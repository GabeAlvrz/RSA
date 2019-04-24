# MyRSA
MyRSA is a simple RSA encryption program

## Compilation
Use the javac command to compile MyRSA.java
```bash
javac MyRSA.java
```

## Usage
After compiling use the following commands to run the processes;

## Encode
To turn an ascii string into hex
```bash
java MyRSA --encode [STRING] # returns hex string
```
Example
```bash
java MyRSA --encode "Hello" # returns 68656c6c6f
```
If no string is provided then user will be prompted for input

## Decode
To turn a hex string into ascii string
```bash
java MyRSA --decode [HEXSTRING] # returns ascii string
```
Example
```bash
java MyRSA --decode "68656c6c6f" # returns hello
```
If no string is provided then user will be prompted for input

## Generate Key
To generate private and public keys
```bash
java MyRSA --genKey # returns e, d, and n values
```
Example
```bash
java MyRSA --genKey # returns n: 221659982060207, e: 65537, d: 86851792295273
```

## Encryption
To encrypt a message

NOTE: Message must be in hex format. Also, e and n are integers.
```bash
java MyRSA --encrypt e n [MESSAGE] #returns encrypted message
```
Example
```bash
java MyRSA --encrypt 65537 221659982060207 "68656c6c6f" # returns 874d0e6ec47d
```
If no message is provided then user will be prompted for input

## Decryption
To decrypt a message

NOTE: Message must be in hex format. Also, e and n are integers.
```bash
java MyRSA --encrypt d n [MESSAGE] #returns encrypted message
```
Example
```bash
java MyRSA --encrypt 86851792295273 221659982060207 "874d0e6ec47d" # returns 0068656c6c6f
```
If no message is provided then user will be prompted for input