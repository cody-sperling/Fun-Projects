import pyqrcode
import png
import os
from pyqrcode import QRCode








os.system("cls")
web_string = input("Please type the full URL of the website you are linking the QR Code to: ")
os.system("cls")
filename = input("Give a file name for the Image:")


url = pyqrcode.create(web_string)

inputs = input("Would you like a svg file or a png file? ")


if inputs == "svg":
    full_file = filename + ".svg"
    url.svg(full_file, scale = 8)
    os.system(f"move {full_file} C:\\Users\\Codyl\\QRS\\{full_file}")

elif inputs == "png":

    full_file = filename + ".png"
    url.png(full_file, scale = 6)
    os.system(f"move {full_file} C:\\Users\\Codyl\\QRS\\{full_file}")
