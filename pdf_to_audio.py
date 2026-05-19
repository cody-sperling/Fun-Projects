import PyPDF2
import pyttsx3
import os


os.system("cls")
pdf_filename = input("Enter pdf file name with extension: ").strip()

try:
    with open(pdf_filename,"rb") as pdf_file:

        pdf_reader = PyPDF2.PdfReader(pdf_file)


        speak = pyttsx3.init()


        for page_num in range (len(pdf_reader.pages)):
            page = pdf_reader.pages[page_num]
            text = page.extract_text()

            if text:
                speak.say(text)

                speak.runAndWait()


            speak.stop()


except FileNotFoundError:
    print("File wasn't found")


