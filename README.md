# Json-Mapper-Util

Json-Mapper-Util is a java library to convert a json string from one structure to another structure.
It provides simple methods to perform this operation. 

## Getting Started

This project should be used as a library for another project and do not provide any standalone functionality.
For viewing the source code, clone the repository and import as normal maven project inside desired IDE.

### Prerequisites

Needs Maven for dependencies like Gson


```
1. Include this libary as a dependency to your Maven/Gradle project
2. Create a Mapping file written in JSON Format. The purpose of this file is to provide the definition 
   for the library to transform one json structure into other.
2.1 A sample mapping file is written below
   
    {
     "sourceKey1":"destinationKey1",
     "sourceKey2.innerKey":"destinationKey2.innerKey1",
     "sourceKey3.innerKey":"destinationKey2.innerKey2"
    }
2.2 The file basically tells the libary how to map the source and desination json object. 
    In the above example the first mapping shows that the value of "sourceKey1" in the source JSON should be
    attached to "destinationKey1" in the target json.
2.3 DOT(.) in the above example signifies the inner properties of the object

3. So if the souce JSON is as below
  { 
     "sourceKey1":"Value1",
     "sourceKey2":{ 
        "innerKey":"Value2"
     },
     "sourceKey3":{ 
        "innerKey":"Value3"
     }
  }

   Using the mapping defined, this will be transformed like below
   
 { 
    "destinationKey1":"Value1",
    "destinationKey2":{ 
       "innerKey1":"Value2",
       "innerKey2":"Value3"
    }
 }

```


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing


## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Abhishek Vaze @ Invicto Solutions** - *Initial work* - [InvictoSolution](https://github.com/invicto)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

