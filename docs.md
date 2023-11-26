## Documentation ( What i learn => with examples )
    ```diff
    - text in red
    + text in green
    ! text in orange
    # text in gray
    @@ text in purple (and bold)@@

1. What is mapstruct?
- There are 2 classes (pojo class usually).And there can be similarities and differences in properties , we change these properties to one class property that is similar for two or more classes.
- MapStruct is a Java-based code generation library that simplifies the implementation of mappings between Java bean types. It is particularly useful for situations where you need to transform data between objects with different structures, such as DTOs (Data Transfer Objects), entities, and other domain objects.
  - // Source class
  

    public class Source {
      private String name;
      private int age;

    // getters and setters
    }

// Target class

    public class Target {
      private String fullName;
      private int age;

    // getters and setters
    }

// Mapper interface

    @Mapper
    public interface MyMapper {
      MyMapper INSTANCE = Mappers.getMapper(MyMapper.class); 
  
      @Mapping(source = "name", target = "fullName")
      Target sourceToTarget(Source source);
    }

// Usage

    Source source = new Source("John Doe", 25);
    Target target = MyMapper.INSTANCE.sourceToTarget(source);
