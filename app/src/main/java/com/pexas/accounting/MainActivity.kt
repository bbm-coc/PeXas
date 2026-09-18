package com.pexas.accounting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.pexas.accounting.data.*
import com.pexas.accounting.ui.AccountingViewModel

private val menus = listOf(
    "Dashboard",
    "Company",
    "Administration",
    "Transactions",
    "Account Books",
    "Inventory",
    "GST Reports",
    "MIS Reports",
    "Print / Email / SMS",
    "House-Keeping"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { AccountingApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountingApp(vm: AccountingViewModel = viewModel()) {
    val nav = rememberNavController()
    var search by remember { mutableStateOf("") }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = androidx.compose.ui.graphics.Color(0xFF1261A0),
            secondary = androidx.compose.ui.graphics.Color(0xFF4D6B82),
            surface = androidx.compose.ui.graphics.Color(0xFFF7F9FC)
        )
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Pexas Accounting & Billing", fontWeight = FontWeight.Bold) },
                    actions = {
                        IconButton(onClick = { search = "" }) { Icon(Icons.Default.Search, "Global Search") }
                        IconButton(onClick = {}) { Icon(Icons.Default.Notifications, "Notifications") }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = nav.currentDestination?.route == "dashboard",
                        onClick = { nav.navigate("dashboard") },
                        icon = { Icon(Icons.Default.Home, null) },
                        label = { Text("Home") }
                    )
                    NavigationBarItem(
                        selected = nav.currentDestination?.route == "sales",
                        onClick = { nav.navigate("sales") },
                        icon = { Icon(Icons.Default.ReceiptLong, null) },
                        label = { Text("Sales") }
                    )
                    NavigationBarItem(
                        selected = nav.currentDestination?.route == "purchase",
                        onClick = { nav.navigate("purchase") },
                        icon = { Icon(Icons.Default.ShoppingCart, null) },
                        label = { Text("Purchase") }
                    )
                    NavigationBarItem(
                        selected = nav.currentDestination?.route == "masters",
                        onClick = { nav.navigate("masters") },
                        icon = { Icon(Icons.Default.Settings, null) },
                        label = { Text("Masters") }
                    )
                }
            }
        ) { padding ->
            NavHost(navController = nav, startDestination = "dashboard", modifier = Modifier.padding(padding)) {
                composable("dashboard") { Dashboard(nav, vm, search) }
                composable("sales") {
                    TransactionTreeScreen(vm, "Transactions")
                }
                composable("purchase") {
                    TransactionTreeScreen(vm, "Transactions")
                }
                composable("masters") { MastersScreen(nav, vm) }
                composable("companies") { CompanyScreen(vm) }
                composable("party") { PartyScreen(vm) }
                composable("items") { ItemScreen(vm) }
                composable("accountbooks") { AccountBooksScreen() }
                composable("reports") { ReportsScreen() }
                composable("administration") { AdministrationScreen(nav) }
                composable("utilities") { UtilitiesScreen() }
            }
        }
    }
}

@Composable
fun Dashboard(nav: NavHostController, vm: AccountingViewModel, search: String) {
    val sales by vm.sales.collectAsState()
    val purchases by vm.purchases.collectAsState()
    val parties by vm.parties.collectAsState()
    val items by vm.items.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item { Text("My Dash Board", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold) }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                KpiCard("Total Units", items.size.toString(), Modifier.weight(1.4f))
                KpiCard("Consignment", sales.size.toString(), Modifier.weight(1f))
                KpiCard("Transporter", parties.size.toString(), Modifier.weight(1f))
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                ActionCard(
                    "Sales",
                    "Add / Modify / List",
                    Icons.Default.ReceiptLong,
                    modifier = Modifier.weight(1f)
                ) { nav.navigate("sales") }
                ActionCard(
                    "Purchase",
                    "Order / Bill / Return",
                    Icons.Default.ShoppingCart,
                    modifier = Modifier.weight(1f)
                ) { nav.navigate("purchase") }
            }
        }
        item { SectionTitle("Account Books") }
        item {
            MenuGroup(listOf(
                "Day Book" to "accountbooks",
                "Account Ledger" to "accountbooks",
                "Cash/Bank Book" to "accountbooks",
                "Account Registers (Standard)" to "accountbooks",
                "Account Registers (Columnar)" to "accountbooks",
                "Account Activity Report" to "reports",
                "Party Day Book" to "accountbooks"
            )) { route -> nav.navigate(route) }
        }
        item { SectionTitle("Inventory & Reports") }
        item {
            MenuGroup(listOf(
                "Stock Status" to "items",
                "Inventory Books" to "reports",
                "Inventory Summary" to "reports",
                "MRP/Param.-wise Reports" to "reports",
                "Quotation/Order/Challan Processing" to "reports",
                "GST Reports" to "reports",
                "MIS Reports" to "reports",
                "Sales Analysis" to "reports",
                "Purchase Analysis" to "reports",
                "Issue-Receipt Analysis" to "reports",
                "Consumption Analysis" to "reports",
                "Item Receivable/Issuable" to "reports"
            )) { route -> nav.navigate(route) }
        }
    }
}

@Composable
fun KpiCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RoundedCornerShape(14.dp)) {
        Column(Modifier.padding(14.dp)) {
            Text(title, style = MaterialTheme.typography.labelMedium)
            Spacer(Modifier.height(5.dp))
            Text(value, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ActionCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(onClick = onClick, modifier = modifier, shape = RoundedCornerShape(14.dp)) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, modifier = Modifier.size(30.dp))
            Spacer(Modifier.width(10.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold)
                Text(subtitle, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
}

@Composable
fun MenuGroup(items: List<Pair<String,String>>, onClick: (String) -> Unit) {
    Card {
        Column {
            items.forEach { (label, route) ->
                Row(
                    Modifier.fillMaxWidth().clickable { onClick(route) }.padding(horizontal = 14.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.ChevronRight, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(label, fontWeight = FontWeight.SemiBold)
                }
                HorizontalDivider()
            }
        }
    }
}


@Composable
fun TransactionTreeScreen(vm: AccountingViewModel, title: String) {
    val transactions = listOf(
        "Sales Quotation",
        "Sales Order",
        "Sales",
        "Purchase Order",
        "Purchase",
        "Sales Return (Cr. Note)",
        "Purchase Return (Dr. Note)",
        "Payment",
        "Receipt",
        "Journal",
        "Contra",
        "Dr. Note (w/o Items)",
        "Cr. Note (w/o Items)",
        "Stock Transfer",
        "Production",
        "Unassemble",
        "Stock Journal",
        "Mat. Issued to Party",
        "Mat. Rcvd. from Party",
        "Physical Stock",
        "GST Misc. Utilities"
    )

    TransactionTree(
        title = title,
        transactions = transactions,
        onAction = { transaction, action ->
            // The tree is now ready for every transaction.
            // Individual Add/Modify/List forms can be connected here one by one.
        }
    )
}

@Composable
fun SalesScreen(vm: AccountingViewModel) {
    val sales by vm.sales.collectAsState()
    var invoice by remember { mutableStateOf("") }
    var party by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        ScreenHeader("Sales", "Add • Modify • List")
        FormField("Invoice No", invoice) { invoice = it }
        FormField("Party", party) { party = it }
        FormField("Amount", amount) { amount = it }
        Button(
            onClick = { vm.addSale(invoice, "18-09-2026", party, amount.toDoubleOrNull() ?: 0.0); invoice=""; amount="" },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Save Sales Invoice") }
        Spacer(Modifier.height(18.dp))
        Text("Recent Sales", fontWeight = FontWeight.Bold)
        sales.forEach { Text("• ${it.invoiceNo}  |  ${it.party}  |  ₹${"%.2f".format(it.amount)}", Modifier.padding(vertical = 6.dp)) }
    }
}

@Composable
fun PurchaseScreen(vm: AccountingViewModel) {
    val purchases by vm.purchases.collectAsState()
    var bill by remember { mutableStateOf("") }
    var party by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        ScreenHeader("Purchase", "Purchase Order • Bill • Return")
        FormField("Bill No", bill) { bill = it }
        FormField("Party", party) { party = it }
        FormField("Amount", amount) { amount = it }
        Button(
            onClick = { vm.addPurchase(bill, "18-09-2026", party, amount.toDoubleOrNull() ?: 0.0); bill=""; amount="" },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Save Purchase") }
        Spacer(Modifier.height(18.dp))
        Text("Recent Purchases", fontWeight = FontWeight.Bold)
        purchases.forEach { Text("• ${it.billNo}  |  ${it.party}  |  ₹${"%.2f".format(it.amount)}", Modifier.padding(vertical = 6.dp)) }
    }
}

@Composable
fun MastersScreen(nav: NavHostController, vm: AccountingViewModel) {
    val groups = listOf(
        "Account", "Account Group", "Std. Narration", "Item", "Item Group",
        "Material Centre", "Material Centre Group", "Unit", "Unit Conversion",
        "Bill Sundry", "Bill of Material", "Sale Type", "Purchase Type",
        "Tax Category", "Misc. Masters"
    )
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        ScreenHeader("Administration → Masters", "Master data used by transactions")
        groups.forEach { name ->
            val route = when (name) {
                "Item" -> "items"
                "Account", "Account Group" -> "party"
                else -> "reports"
            }
            Card(onClick = { nav.navigate(route) }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ChevronRight, null)
                    Spacer(Modifier.width(8.dp))
                    Text(name, fontWeight = FontWeight.SemiBold)
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Card(onClick = { nav.navigate("administration") }) {
            Text("Configuration", Modifier.padding(16.dp), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CompanyScreen(vm: AccountingViewModel) {
    val companies by vm.companies.collectAsState()
    var name by remember { mutableStateOf("") }
    var fy by remember { mutableStateOf("2026-27") }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        ScreenHeader("Company", "Open Company • Create Company • Backup • Restore")
        FormField("Company Name", name) { name = it }
        FormField("Financial Year", fy) { fy = it }
        Button(onClick = { vm.addCompany(name, fy); name="" }, Modifier.fillMaxWidth()) { Text("Create Company") }
        Spacer(Modifier.height(16.dp))
        companies.forEach { Text("• ${it.name}  (${it.financialYear})", Modifier.padding(6.dp)) }
        listOf("Open Company", "Backup Data", "Restore Data", "Split Financial Year", "Delete Company / Single F.Y.", "Reindex Databases").forEach {
            OutlinedButton(onClick = {}, Modifier.fillMaxWidth().padding(top = 6.dp)) { Text(it) }
        }
    }
}

@Composable
fun PartyScreen(vm: AccountingViewModel) {
    val parties by vm.parties.collectAsState()
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var gstin by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        ScreenHeader("Account / Party Master", "Customer • Supplier • Transporter")
        FormField("Name", name) { name=it }
        FormField("Phone", phone) { phone=it }
        FormField("GSTIN", gstin) { gstin=it }
        FormField("Address", address) { address=it }
        Button(onClick={ vm.addParty(name,phone,gstin,address); name="" }, Modifier.fillMaxWidth()) { Text("Save Party") }
        Spacer(Modifier.height(16.dp))
        parties.forEach { Text("• ${it.name}  |  ${it.phone}  |  ${it.gstin}", Modifier.padding(6.dp)) }
    }
}

@Composable
fun ItemScreen(vm: AccountingViewModel) {
    val items by vm.items.collectAsState()
    var name by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("Nos") }
    var sale by remember { mutableStateOf("") }
    var purchase by remember { mutableStateOf("") }
    var gst by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        ScreenHeader("Item Master", "Item • Group • Unit • GST • Rates")
        FormField("Item Name", name) { name=it }
        FormField("Unit", unit) { unit=it }
        FormField("Sale Rate", sale) { sale=it }
        FormField("Purchase Rate", purchase) { purchase=it }
        FormField("GST %", gst) { gst=it }
        Button(onClick={ vm.addItem(name,unit,sale.toDoubleOrNull()?:0.0,purchase.toDoubleOrNull()?:0.0,gst.toDoubleOrNull()?:0.0); name="" }, Modifier.fillMaxWidth()) { Text("Save Item") }
        Spacer(Modifier.height(16.dp))
        items.forEach { Text("• ${it.name} | ${it.unit} | Sale ₹${it.saleRate} | GST ${it.gstPercent}%", Modifier.padding(6.dp)) }
    }
}

@Composable
fun AccountBooksScreen() {
    ReportList("Account Books", listOf(
        "Day Book", "Account Ledger", "Cash/Bank Book", "Account Registers (Standard)",
        "Account Registers (Columnar)", "Account Activity Report", "Party Day Book",
        "Account Summary", "Outstanding Analysis", "Interest Calculation",
        "Depreciation Chart", "Bank Reconciliation"
    ))
}

@Composable
fun ReportsScreen() {
    ReportList("Reports & Analysis", listOf(
        "Stock Status", "Inventory Books", "Inventory Summary", "MRP/Param.-wise Reports",
        "Quotation / Order / Challan Processing", "GST Reports", "MIS Reports", "Check List",
        "Sales Analysis", "Purchase Analysis", "Issue-Receipt Analysis",
        "Consumption Analysis", "Item Receivable / Issuable", "GST Misc. Utilities"
    ))
}

@Composable
fun AdministrationScreen(nav: NavHostController) {
    ReportList("Administration", listOf(
        "Users", "Utilities", "Bulk Updations", "Data Export / Import",
        "Miscellaneous Data Entry", "Change Financial Year"
    ))
}

@Composable
fun UtilitiesScreen() {
    ReportList("Utilities", listOf("Data Import", "Data Export", "Database Backup", "Database Restore", "Reindex Databases"))
}

@Composable
fun TransactionTree(
    title: String,
    transactions: List<String>,
    onAction: (transaction: String, action: String) -> Unit
) {
    var expanded by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(bottom = 20.dp)
    ) {
        item { ScreenHeader(title, "Transactions") }

        items(transactions) { transaction ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                expanded = if (expanded == transaction) null else transaction
                            }
                            .padding(horizontal = 14.dp, vertical = 13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            if (expanded == transaction) Icons.Default.ExpandMore else Icons.Default.ChevronRight,
                            null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(transaction, fontWeight = FontWeight.Bold)
                    }

                    if (expanded == transaction) {
                        HorizontalDivider()
                        listOf("Add", "Modify", "List").forEach { action ->
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .clickable { onAction(transaction, action) }
                                    .padding(start = 46.dp, end = 14.dp, top = 10.dp, bottom = 10.dp)
                            ) {
                                Icon(Icons.Default.ChevronRight, null, modifier = Modifier.size(18.dp))
                                Spacer(Modifier.width(6.dp))
                                Text(action, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReportList(title: String, rows: List<String>) {
    LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        item { ScreenHeader(title, "Select an option") }
        items(rows) { row ->
            Card(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ChevronRight, null)
                    Spacer(Modifier.width(8.dp))
                    Text(row, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
fun ScreenHeader(title: String, subtitle: String) {
    Column(Modifier.padding(bottom = 10.dp)) {
        Text(title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(subtitle, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun FormField(label: String, value: String, onValueChange: (String)->Unit) {
    OutlinedTextField(
        value=value, onValueChange=onValueChange, label={Text(label)},
        modifier=Modifier.fillMaxWidth().padding(vertical=5.dp), singleLine=true
    )
}
