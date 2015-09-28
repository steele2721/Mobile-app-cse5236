#  Roommate App
For CSE 5236

I wrote this keeping in mind all the issues we had with GitHub over the summer, so if I've written something that seems obvious or basic, I'm just trying to make sure we're all on the same page.

Note: since this README lives in the Android project directory, you may want to install the Markdown plugin.

## Getting Started
### Getting latest verison using Android Studio (recommended)
1. Start Android Studio and click on "Check out version from version control" and then click GitHub
2. Enter your GitHub login information if you haven't already
3. Enter your master password if prompted
4. In the "clone repository" window, in the dropdown box labeled "Git Reposotory URL" select "https://github.com/steele2721/Mobile-app-cse5236.git"
5. Select "Clone" and open the project
6. If it says that there is an unrecognized VCS root, select "Add Root"

### Getting latest version using git (for the adventurous)
1. Go into the directory you want the project to live in
2. In the Linux shell, assuming you have git installed, type `git clone https://github.com/steele2721/Mobile-app-cse5236.git` for HTTPS or `git clone git@github.com:steele2721/Mobile-app-cse5236.git` for SSH (these are the links on the right of this page)
3. Android Studio can add all the information you just cloned to its own VCS automatically

At this point, you should have the latest version of the project. If you build the project, it should succeed. 

## Version control
Please read about [the git workflow](https://guides.github.com/introduction/flow/index.html) to understand how to get the latest verison of the project, and see [the git workflow cheatsheet](http://rogerdudler.github.io/git-guide/) for the relevant commands.

TL;DR, each time you want to add a new feature to the project (and by feature I mean that in the most basic sense), it should go something like this:

1. Checkout the latest version of the project from the master branch, and create a new branch (your branch should be named after the feature you are going to implement). To do this in Android Studio, go to VCS -> Git -> Branches -> Create New Branch
2. Make changes, commit each change (optionally, push each change if you're working between multiple computers)
3. Open a pull request so we can look at what you've done before it's merged with the master branch (optional I think, though it's nice if you're not sure about something)
4. Merge with master

The idea behind this is so that **you're not pushing broken code to the master branch**, and to see incremental changes to the code on GitHub. Here's a [useful article about branching](http://nvie.com/posts/a-successful-git-branching-model/).

## Using GitHub
Though we will be seeing each other like three times a week over the course of this project, I would like to point out some of the useful features of GitHub.

### The issues page
This should be used for tracking and reporting known issues, including bugs, TODOs, and so forth. This can be used in conjunction with merges and pull requests.

### The code page
Here you can see various versions of the code and from here get previous versions if necessary.

## Other Useful Information
* GitHub supports [Markdown](https://daringfireball.net/projects/markdown/syntax) in nearly every text field on this site, which is how I wrote this very stylish document. I would especially look at the "code" section.
* Every directory can have its own README page if necessary.

If I think of anything else to write here I'll update as needed.
