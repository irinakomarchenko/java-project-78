### Hexlet tests and linter status:
[![Actions Status](https://github.com/irinakomarchenko/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/irinakomarchenko/java-project-78/actions)

[![Maintainability](https://api.codeclimate.com/v1/badges/c65b8b349730e5490082/maintainability)](https://codeclimate.com/github/irinakomarchenko/java-project-78/maintainability)

[![Test Coverage](https://api.codeclimate.com/v1/badges/c65b8b349730e5490082/test_coverage)](https://codeclimate.com/github/irinakomarchenko/java-project-78/test_coverage)

[![CI](https://github.com/irinakomarchenko/java-project-78/actions/workflows/ci.yml/badge.svg)](https://github.com/irinakomarchenko/java-project-78/actions/workflows/ci.yml)

[**Project "Data Validator"**]()

Library for checking data correctness

**[Description]()**

A validator object is created, a data validation scheme is created and configured. 
After that, the data is validated using the previously created scheme.
```
var v = new Validator();
var schema = v.string();
schema.isValid("Hello world")();
```


**Data Validation Schemes**

**StringSchema** contains the following set of methods:

required() — makes the data mandatory to fill. In other words, adds a constraint to the schema that does not allow 
null or an empty string to be used as a value
minLength() — adds a minimum length constraint for a string to the schema. 
The string must be equal to or longer than the specified number
contains() — adds a constraint on the contents of the string to the schema. 
The string must contain a certain substring

**NumberSchema** used to validate numbers.

The schema contains the following set of methods:

required() — adds a constraint to the schema that does not allow null to be used as a value
positive() — adds a constraint on the sign of the number. The number must be positive
range() — adds an acceptable range that the value of the number must fall within, 
including its boundaries

**MapSchema** used to validate Map objects.

The schema contains the following methods:

required() — adds a constraint to the schema that does not allow null to be used as a value. 
The Map data type is required
sizeof() — adds a constraint on the size of the map. The number of key-value pairs in the 
Map object must be equal to the specified
The shape() method is used to define the properties of a Map object and create a schema for validating
their values. Each property of a Map object is assigned its own set of constraints (its own schema),
which allows for more precise control over the data
