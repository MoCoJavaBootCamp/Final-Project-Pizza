# Final-Project-Pizza

## Team
| Name | Responsibilities |
|---|---|
| [Andrew (azdoolittle)](https://github.com/azdoolittle) | SecConfig, Role, RoleRepo, Reports, ReportsRepo |
| [Eyasu (eyasuh)](https://github.com/eyasuh) | User, UserRepo, Register, Base fragment template |
| [Jaeha (jsong073)](https://github.com/jsong073) | Pizza, PizzaRepo |
## Pages
- home
- login
- menu
- userinfo
- admin
- formPage

## Database Tables

### User
| id | firstName | lastName | email  | password | phone | street | city | zip | orders |
|---|---|---|---|---|---|---|---|---|---|
| long | string | string | string | string | long | string | string | long | long |

### Role
| id | user | role | 
|---|---|---|
| long | string | string |

### Pizza
| id | name | toppings | price  | user | dateTime |
|---|---|---|---|---|---|
| long | string | ArrayList`<long>` | double | long | dateTime |

### Topping
| id | name | price  | count | 
|---|---|---|---|
| long | string | double | long |

### Report
| id | totalSales | topToppings | 
|---|---|---|
| long | double | ArrayList`<long>`|
