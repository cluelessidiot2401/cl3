from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.firefox.firefox_binary import FirefoxBinary

binary = FirefoxBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe")
driver = webdriver.Firefox(firefox_binary=binary,executable_path=r'path_to_gecko_driver')

driver.get("http://localhost:5000")
text1 = driver.find_element_by_name("num1")
text2 = driver.find_element_by_name("num2")
text1.send_keys("30")
text2.send_keys("21")
text1.send_keys(Keys.RETURN)
assert "630" in driver.page_source
driver.close()