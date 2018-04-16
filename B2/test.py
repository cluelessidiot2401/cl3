from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.firefox.firefox_binary import FirefoxBinary

binary = FirefoxBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe")
driver = webdriver.Firefox(firefox_binary=binary,executable_path=r'C:\\Users\\vikra\\Documents\\Github\\cl3\\geckodriver.exe')

driver.get("http://localhost:5000")
text1 = driver.find_element_by_name("inp")
text1.send_keys("Peripheral devices allow information to be retrieved from an external source and they enable the result of operations to be saved and retrieved. Hello mate. Early computers were only conceived as calculating devices. And there it is")
# text1.send_keys(Keys.RETURN)
driver.find_element_by_name("plagiarismChecker").submit()
assert "50" in driver.page_source
driver.close()