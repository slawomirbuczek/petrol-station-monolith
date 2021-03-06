# About:
Petrol station is my university project. I am responsible for writing the backend.  
Ultimately the project will be deployed on Heroku.

## Petrol station:
Petrol station is equipped with 4 self-service dispensers and one LPG station.  
Each fuel pump can refuel: E95, E98, ON. The automatic car wash has two stands.  
After refueling, the driver goes to the cash register and pays for fuel.  
The station is equipped with a video monitoring system and a monitoring monitoring system.  

## Current functionalities: 
### Registration:
* The customer can register as an individuals or as a companies.
* During registration, the customer must enter all the addresses and contact details necessary to issue an invoice.
* After registration, the customer receives a unique id number and password.
### Login:
* Customer can log in with their id or by email and password.
### User service:
* Forgotten password and email can be changed after confirming the action via the token sent to the email.
* Each customer can modify their own data. 
* The owner and authorized employees are able to manage customer data.
### Loyalty program:
* Registered customer has the option to join the loyalty program.
* For each service, the customer gets loyalty points, for example (with default monitoring):
  * each liter of petrol and diesel fuel (E95, E98, ON) - 2 points;
  * each liter of LPG - 1 point;
  * standard washing - 5 points;
  * washing with waxing - 10 points;
* Points can be exchanged for additional fuel or washig, for example (with default monitoring):
  * petrol and diesel fuel (E95, E98, ON) - 100 points per liter;
  * LPG - 50 points per liter;
  * standard washing - 300 points per service;
  * washing with waxing - 400 points per service;
* Points are automatically awarded after each trnsaction.
### Car wash:
* Each registered customer can book a car wash.
* Car wash can be booked from 6 am to 11 pm.
* Each booking lasts 30 minutes.
* The customer can cancel their own booking.
* Authorized employees are able to view the booking details.
### Price list:
* Everyone can view the current prices of services.
* The price list can be changed by the owner.
### Transactions:
* Unregistered customer can obtain a receipt.
* Each transactions is recorded in the datebase.
* Registered customers can view their transactions.
### Invoices:
* Registered customer can receive an invoice on request.
### Monitoring:
* The system monitors the current monitoring in the tanks, the monitored monitoring are:
  * fuel level in petrol and diesel (E95, E98, ON) tanks, LPG level;
  * pressure above the liquid surface in E95, E98, ON tanks and LPG vapor pressure;
  * temperature in the tanks;
* Parameters are saved in the database at the specified frequency.
* The monitoring frequency can be changed by authorized employees at runtime.
* Authorized employees are able to view current and history values.
* In critical situations a message is sent via email to authorized employees.
* Critical situations:
  * low fuel level in the tank (fuel delivery required);
  * permissible pressure or temperature value in the tank is exceeded;
  * activation of the safety valve in a given tank;
  * sensor failure;
* Currently the system generaters random monitoring values.
### Reports:
* The owner and authorized employees are able to obtain a monthly sales and monitoring report.
* The monthly sales report cantais data:
  * sum of the liters of fuel and LPG sold;
  * amount of car wash usage divided into available service;
  * total profit from each service;
* The monthly monitoring report contains all record monitored in given month.
### Supplies:
* The owner can manage data about orgianization of fuel supplies.
### Simulations:
* The owner can simulate critical situations to test the operation of the system.
