import base64
def encode(key, clear):
    encryptedString = ""
    for i in range(len(clear)):
        key_c = key[i % len(key)]
        enc_c = chr((clear[i] + key_c) % 256)
        encryptedString += enc_c
    return encryptedString

def decode(key, enc):
    dec = []
    for i in range(len(enc)):
        key_c = key[i % len(key)]
        dec_c = chr((256 + ord(enc[i]) - ord(key_c)) % 256)
        dec.append(dec_c)
    return "".join(dec)

if __name__ == "__main__":
    print("Enter key to use for encryption")
    key = str(input())

    print("Enter password to be encrypted")
    message = str(input()).encode()

    print("")

    encryptedString=encode(key.encode(),message)
    print("Encoded String = ",encryptedString)
    print("Encoded String in byte Format= ",encryptedString.encode())
    decodedString = decode(key,encryptedString)
    print("Decoded String = ",decodedString)