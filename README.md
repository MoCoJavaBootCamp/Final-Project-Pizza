# Final-Project-Pizza

## Team
| Name | Responsibilities |
|---|---|
| [Andrew (azdoolittle)](https://github.com/azdoolittle) | SecConfig, Role, RoleRepo, Reports, ReportsRepo, Nav |
| [Eyasu (eyasuh)](https://github.com/eyasuh) | User, UserRepo, Register, Base fragment template |
| [Jaeha (jsong073)](https://github.com/jsong073) | Pizza, PizzaRepo, Ordering, Menu |
## Pages
- home
- login
- menu
- userinfo
- admin
- formPage

## Database Tables

### User
| id | firstName | lastName | email  | password | phone | street | city | zip | pizzas | enabled | 
|---|---|---|---|---|---|---|---|---|---|---|
| long | string | string | string | string | long | string | string | long | Set`<Pizza>` | boolean |

### Role
| id | user | role | 
|---|---|---|
| long | string | string |

### Pizza
| id | name | sauce | toppings | price  | user | date |
|---|---|---|---|---|---|---|
| long | string | string | Set`<Topping>` | double | User | LocalDateTime |

### Topping
| id | name | price  | count | pizzas |
|---|---|---|---|---|
| long | string | double | long | Set`<Pizza>` |

### Report
| id | totalSales | topToppings | users |
|---|---|---|---|
| long | double | Set`<Topping>`| Set`<User>` |
