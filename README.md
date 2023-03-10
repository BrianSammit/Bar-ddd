# Bar-ddd

This app is an order app that is build with DDD. Domain-driven design (DDD) is an approach to developing software
for complex needs by deeply connecting the implementation to an evolving model of the core business concepts.

## Built With

- Java 17
- Spring


## Big Picture, Domain, Subdomains and Bounded context.

![](assets/images/bar%20ddd-Page-1.jpg)

## Command driven use cases

**Order**
- CreateOrderUseCase
- AddDrinkUseCase 
- RemoveDrinkUseCase

**Table**
- CreateTableUseCase
- AddCostumerUseCase
- RemoveCostumerUseCase
- AddWaiterUseCase

**Drink**

[//]: # (**Endpoints:**)

[//]: # ()
[//]: # (| Method | Endpoint | Functionality |)

[//]: # (|:------:|:--------:|:-------------:|)

[//]: # (|POST    |/sessions|Create a current session|)

[//]: # (|POST    |/registrations|Create users|)

[//]: # (|DELETE    |/logout|Delete the session|)

[//]: # (|GET     |/logged_in|To logged in|)

[//]: # (|GET     |/skateboards| Get all skateboards|)

[//]: # (|GET  |/skateboards/${skateboard_id}| Get a single skateboard|)

[//]: # (|GET     |/appointments      |Get all appointments|)

### Setup

To run this project locally, please ensure you have Java installed on your machine, then clone this 
repository by running.

```bash
git clone https://github.com/BrianSammit/Bar-ddd.git
```
Open the project with your favorite IDE. I recommend you to use IntelliJIDEA

### Tests

To check the tests:

- Go to the test folder and chose which test you want to run and click on the play icon next to the test class. 


## Author

👤 **Brian Cruz**

- Github: [@githubhandle](https://github.com/BrianSammit)
- Twitter: [@twitterhandle](https://twitter.com/cruzsammit)
- Linkedin: [linkedin](https://www.linkedin.com/in/brian-sammit-cruz-rodriguez-5877551a8/)

## Contributing

Contributions, issues, and feature requests are welcome!

Feel free to check the [issues page](https://github.com/BrianSammit/React_bookstore/issues).

## Show your support

Give a ⭐️ if you like this project!

## Acknowledgments

- Softka

## License

This project is MIT licensed.!
