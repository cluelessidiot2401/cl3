import hashlib
from pydsa import dsa

dsa_key = {
    'Q': 1218442816993522937915646204915776994404649089503,
    'P': 11220611807188583130302963536190351192186270126479330588604287699892081267588448305835704397593153801135202051719876685351614175538253684346816652027037363,
    'G': 11189361631195852088154673407566885728548496486362662112597687161142104619469702160215294558351391466982303919803857229515093575816938371433954759500448775,
    'pub': 4572510396595314270786423212039255215498677297795049756997099191729339616558419010431226927123876238239229467750410441342637393785565872285607741290303779,
    'priv': 148102768779017960166999813987055538077373228390}
text = """lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod
tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At 
vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd 
gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum 
dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor 
invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero 
eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no 
sea takimata sanctus est Lorem ipsum dolor sit amet."""
m = hashlib.sha1()
m.update(text.encode())
message = int("0x" + m.hexdigest(), 0)
sig = dsa.dsa_sign(dsa_key["Q"], dsa_key["P"], dsa_key["G"], dsa_key["priv"], message)
print ("=" * 80)
print ("DSA SIGNATURE EXAMPLE")
print ("=" * 80)
print ("DSA Keypair:")
for k in dsa_key.keys():
    print (k, ':', str(dsa_key[k]))
print("-" * 80)
print ("Text:")
print (text)
print ("-" * 80)
print ("SHA-1:",end='')
print (message)
print ("-" * 80)
print ("DSA Signature:",end='')
print (sig)
print ("-" * 80)
print ("Verify:",end='')
print (dsa.dsa_verify(sig[0], sig[1], dsa_key["G"], dsa_key["P"], dsa_key["Q"], dsa_key["pub"], message))
print ("-" * 80)