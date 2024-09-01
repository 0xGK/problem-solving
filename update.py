#!/usr/bin/env python

import os
from urllib import parse

HEADER="""# 
# 백준 문제 풀이 목록

"""

def main():
    content = ""
    content += HEADER
    
    directories = []
    solveds = {}

    # Define a custom order for the categories
    custom_order = ["백준", "Bronze", "Silver", "Gold", "Platinum"]
    
    for root, dirs, files in os.walk("."):
        dirs.sort()
        if root == '.':
            for dir in ('.git', '.github'):
                try:
                    dirs.remove(dir)
                except ValueError:
                    pass
            continue

        category = os.path.basename(root)
        
        if category == 'images':
            continue
        
        directory = os.path.basename(os.path.dirname(root))
        
        if directory == '.':
            continue
            
        if directory not in directories:
            directories.append(directory)
            solveds[directory] = []

        for file in files:
            if category not in solveds[directory]:
                solveds[directory].append((category, parse.quote(os.path.join(root, file))))

    # Sort directories according to the custom order
    directories = sorted(directories, key=lambda x: custom_order.index(x) if x in custom_order else len(custom_order))

    # Write content according to sorted directories
    for directory in directories:
        print(directory)
        if directory == "백준":
            content += "## 📚 {}\n".format(directory)
        else:
            content += "### 🚀 {}\n".format(directory)
            content += "| 문제번호 | 링크 |\n"
            content += "| ----- | ----- |\n"
        for category, link in sorted(solveds[directory], key=lambda x: custom_order.index(x[0].lower()) if x[0].lower() in custom_order else len(custom_order)):
            content += "|{}|[링크]({})|\n".format(category, link)

    with open("README.md", "w") as fd:
        fd.write(content)
        
if __name__ == "__main__":
    main()
