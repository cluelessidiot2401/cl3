import pyaes
import pyDes

# A 256 bit (32 byte) key
key = "This_key_for_demo_purposes_only!"
keyDes = "EightLen"
plaintext = "Text may be any length you wish, no padding is required"
desPlaintext = "Text requires padding"

print("Message for AES Encryption= '",plaintext,"'")
print("Message for DES Encryption= '",desPlaintext,"'")

# key must be bytes, so we convert it
key = key.encode()

aes = pyaes.AESModeOfOperationCTR(key)
des = pyDes.des(keyDes, pyDes.CBC, b"\0\0\0\0\0\0\0\0", pad=None, padmode=pyDes.PAD_PKCS5) 
ciphertext = aes.encrypt(plaintext)   
desCiphertext = des.encrypt(desPlaintext)

# show the encrypted data
print (ciphertext)
print (desCiphertext)

# DECRYPTION
# CRT mode decryption requires a new instance be created
aes = pyaes.AESModeOfOperationCTR(key)

# decrypted data is always binary, need to decode to plaintext
decrypted = aes.decrypt(ciphertext).decode()
desDecrypted = des.decrypt(desCiphertext).decode()

print("Decrypted String = '",end='')
print(decrypted,end='')
print("'")

print("Decrypted String = '",end='')
print(desDecrypted,end='')
print("'")

# True
print (decrypted == plaintext)
print (desDecrypted == desPlaintext)