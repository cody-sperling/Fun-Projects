import PyPDF2
import os
from gtts import gTTS


os.system("cls")
# Open the PDF file
input_file = input("Input a filename and path: ")

output_name = input("pick an output file name: ")

language_key = [
            ["en", "English"],
                ["en-us", "English (US)"],
                    ["en-uk", "English (UK)"],
                        ["es", "Spanish"],
                            ["fr", "French"],
                                ["de", "German"],
                                    ["it", "Italian"],
                                        ["pt", "Portuguese"],
                                            ["ru", "Russian"],
                                                ["ja", "Japanese"],
                                                    ["ko", "Korean"],
                                                        ["zh-tw", "Chinese"],
                                                            ["ar", "Arabic"]
                                                            ]

print(f"{"Language":<15}{"Keyword":<10}")
for lang in language_key:
    print(f"{lang[1]:<15}{lang[0]:<10}")

choice = input("\nSelect a Language: ").lower()

        # Open PDF safely using 'with'
text = ""

with open(input_file, "rb") as pdf_file:
        pdf_reader = PyPDF2.PdfReader(pdf_file)

        # NEW PyPDF2 page access
        for page in pdf_reader.pages:
            page_text = page.extract_text()
            if page_text:   # Prevent None errors
                text += page_text + "\n"

# Convert text to speech AFTER collecting all text
if text.strip():
    tts = gTTS(text=text, lang=choice)
    tts.save("C:\\Users\\Codyl\\Projects\\mp3\\" + output_name + ".mp3")
    print("MP3 created successfully!")
else:
    print("No readable text found in PDF.")

