# cs-180-project-5-group


Post:

the post class provides an outline for everything a post needs in order to be displayed and stored.  
Each post stores the user object that created it, the name of that user, the text to be displayed
in the post, the time stamp it was created, the time in string form, the location of where it is
on the screen, and an ArrayList of comments that have been made on it.

There are getter methods for every field in Post, but there are only setter methods for the 
panel location and comments because these are the only things that can change.  When a post is 
edited, the old post is overwritten, so there is no need to have a setter for anything else.

The formatPost method limits the number of characters that can be on one line in a post to
make it more readable.  

The sort method sorts an ArrayList that is given to it based on the option in the parameter.
This is so that it works with just the user's posts or all posts made.

Testing done on Post includes ensuring that sort doesn't attempt to sort an empty arrayList,
and that the creation of one post does not hinder another post.  Post methods are only called
in the Poster class, to limit accessibility to it.


Poster:

The Poster class methods are called in PostGUI.  

The createPost method creates a formatted post, updates the user's post field 
and writes it to a unique file.  Each user has their own file to prevent them
from editing or deleting other user's posts.  There are fail-safes on this 
method : it will not attempt to create a post if either the user is null or 
the postString is null.  This could happen if something was read from a file
incorrectly.

The editPost method prompts the user through a series of JOptionPane dialogs
to edit a post.  It allows the user to cancel at any time and leave the post
as is.  If the user selects to edit the post, they are prompted for the 
new text.  If the text isn't null or blank, a new post is created and put
in the place of the old post, essentially overwriting the previous post.
Testing on this method includes ensuring that users can only edit
their own posts by giving them their own individual file.  We also
tested whether it would allow multiple edits of the same post, which it does.

The writeToFile method writes every post in userPosts into their unique file.
This is done whenever a change is made to a post.  It also writes in the
comments on the post, if they are present.  Testing on this included
ensuring that it never threw a FileNotFound exception - users are
automatically assigned their own file with their username, and since duplicate
users can't exist (registration doesn't allow duplicate users), this works.
It doesn't attempt to write the file if the ArrayList of posts is null,
which prevents a nullPointerException.

The readFromFile method reads every post in the user's file and returns 
the ArrayList of posts.  It also reads in the comments on the posts.  
Whenever userPosts is referenced, readFromFile is called first to ensure 
that the ArrayList is up to date.  The testing done on this led to us
adding a selection statement to prevent the method from trying to create
posts out of an empty file.  If there is nothing to read, it returns null.
In turn, PostGUI checks if the ArrayList is null before attempting to retrieve any
element.  We also made it so that it doesn't attempt to read comments if there aren't
any to read by checking the size of the string read in.

The findPost method finds a post in either the userPosts or allPosts ArrayList
based on the option parameter in the call.  This is so that it works with either
method. It returns the index of the post if it is found, and the size of the ArrayList
if not.  Testing on this involved ensuring that it reads from the correct file
each time, but doesn't change the contents of the ArrayList.

The readAll method reads all posts made by any user on the platform.  It also
reads in comments on those posts if they exist, but doesn't attempt to if they
don't to prevent an ArrayOutOfBoundsException.  To ensure it worked, we tested
it with empty arrays and for posts with and without comments. We also tested
it for posts made by multiple users to ensure they were distinguishable. 

The writeAll method writes all posts made by any user into a file.  It
also writes the comments made on those posts.  If there are no posts, it
doesn't attempt to write, so there isn't a null pointer or ArrayOutOfBoundsException.
We tested this with empty arrays and for posts with and without comments.  We also tested
it for posts made by multiple users to ensure they were distinguishable. 









