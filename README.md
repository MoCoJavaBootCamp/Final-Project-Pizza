# Final-Project-Pizza
##Intro
PizzaPizza is the leading pizza ordering platform that allows you to create and order pizzas, but also generate detailed reports about your users and sales.

## Team
| Name | Responsibilities |
|---|---|
| [Andrew (azdoolittle)](https://github.com/azdoolittle) | Security, Login, Role, Reports, Nav, Admin |
| [Eyasu (eyasuh)](https://github.com/eyasuh) | User, Register, Admin, Reports, Fragment Templates |
| [Jaeha (jsong073)](https://github.com/jsong073) | Pizza, Menu, Ordering, Checkout, Confirmation|

## Technologies
- Java
- SpringBoot
- Thymeleaf
- Hibernate
- JavaScript
- HTML/CSS (Bootstrap)

## Pages
- home
- login
- menu
- order
- checkout
- confirmation
- myaccount
- orderhistory
- admin
- register
- components

## Database Tables

### User
| id | firstName | lastName | email  | password | phone | street | city | zip | pizzas | enabled | 
|---|---|---|---|---|---|---|---|---|---|---|
| long | String | String | String | String | long | String | String | long | Set`<Pizza>` | boolean |

### Role
| id | user | role | 
|---|---|---|
| long | string | string |

### Pizza
| id | name | specialty | description | sauce | toppings | price  | user | date | image | 
|---|---|---|---|---|---|---|---|---|---|
| long | String | boolean | String | String | Set`<Topping>` | double | User | LocalDateTime | String |

### Topping
| id | name | enabledForUser | price  | count | pizzas |
|---|---|---|---|---|---|
| long | String | boolean | double | long | Set`<Pizza>` |

### Report
| id | totalSales | topToppings | users |
|---|---|---|---|
| long | double | Set`<Topping>`| Set`<User>` |
