# NovusCarta
Initial commit.

Final project for a Grow with Google / Udacity Android Basics scholarship

Project rubric:

Overall Layout

The app contains activities and/or fragments for the user to:

* Add Inventory
* See Product Details
* Edit Product Details
* See a list of all inventory from a Main Activity

Multiple actions listed above can be combined in a single activity.
The user navigates between the activities and/or fragments using one or more of the following navigation patterns - 
Navigation Drawer, View Pager, Up/Back Navigation, or Intents.

List Item Layout in the Main Activity

In the Main Activity/Fragment, each list item displays the Product Name, Price, and Quantity.

Each list item also contains a SaleButton that reduces the total quantity of that particular product by one (include logic 
so that no negative quantities are displayed).

Product Detail Layout

The Product Detail Layout displays the Product Name, Price, Quantity, Supplier Name, and Supplier Phone Number that's stored in 
the database.

The Product Detail Layout also contains buttons that increase and decrease the available quantity displayed.

Add a check in the code to ensure that no negative quantities display (zero is the lowest amount).

The Product Detail Layout contains a button to delete the product record entirely.

The Product Detail Layout contains a button to order from the supplier. In other words, there exists a button to contains a button 
for the user to contact the supplier via an intent to a phone app using the Supplier Phone Number stored in the database.

Default Textview

When there is no information to display in the database, the layout displays a TextView with instructions on how to populate the 
database (e.g. what should be entered in the field, which fields are required).

Layout Best Practices

The code adheres to all of the following best practices:

* Text sizes are defined in sp
* Lengths are defined in dp
* Padding and margin is used appropriately, such that the views are not crammed up against each other.
