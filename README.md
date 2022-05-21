# param-filter
This simply replaces placeholders in files using supported parameter stores.


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
