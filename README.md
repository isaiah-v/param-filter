# param-filter
A filter that replaces placeholders in files using supported parameter stores. For simple cases, this solves my problem of cheking in property files without including secret information.


_Input Example:_
```
Hello, my name is ${env.USER}
```

_Example Output:_
```
Hello, my name is Bob
```

# Useage
```
java -jar param-filter.jar --input=<INPUT_FILE> --output=<OUTPUT_FILE>
```

# Supported Parameter Stores
## Environment Variables
Format: ```${env.name}```

## AWS SSM Paramiters
Format: ```${aws.name}```
