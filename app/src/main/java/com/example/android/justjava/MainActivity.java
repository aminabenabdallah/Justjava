
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

 package com.example.android.justjava;



         import android.content.Intent;
         import android.net.Uri;
         import android.os.Bundle;
         import android.support.v7.app.AppCompatActivity;
         import android.util.Log;
         import android.view.View;
         import android.widget.CheckBox;
         import android.widget.EditText;
         import android.widget.TextView;
         import android.widget.Toast;

         import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int x=0;
    int n=5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view)
    {
        if (x == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        x=x+1;
        displayQuantity(x);
    }

    public void decrement(View view)
    {
        if (x == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        x=x-1;
        displayQuantity(x);
    }



    public void submitOrder(View view) {

        EditText nameField= (EditText) findViewById( R.id.name_field );
         String name = nameField.getText().toString();
         CheckBox whippedCreamCheckBox = (CheckBox) findViewById( R.id.whipped_cream_checkbox );

         boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

         CheckBox chocolateCheckBox = (CheckBox) findViewById( R.id.Chocolate_checkbox );

         boolean hasChocolate = chocolateCheckBox.isChecked();
         int price = calculatePrice(hasChocolate, hasWhippedCream);
          String msg= createOrderSummary( name, price, hasWhippedCream, hasChocolate, hasChocolate, hasWhippedCream );
         Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
         intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for  "+ name);
        intent.putExtra(Intent.EXTRA_TEXT, msg);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }





        /**Intent intent = new Intent( Intent.ACTION_VIEW );
        intent.setData( Uri.parse( "geo:47.6, 122.3" ) );
        if (intent.resolveActivity( getPackageManager() ) != null) {
            startActivity( intent );
        }*/
    }
    /**
     * Calculates the price of the order.
     *
     * quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean hasChocolate, boolean hasWhippedCream)
    {   int a=0;
        if (hasWhippedCream )
        { a= (n + 1) * x;}

       if (hasChocolate)
       {  a= (n + 2) * x;}

       return a;



    }


    private String createOrderSummary(String name, int number, boolean addWhippedCream, boolean addChocolate, boolean hasChocolate, boolean hasWhippedCream)
    {
        return "Name: " +name+ "\n" + "Add whipped Cream? "+ addWhippedCream + "\n"+ "Add Chocolate? "+addChocolate + "\n"
                +"Quantity:" + x+ " \n" +"Total $:"+ calculatePrice(hasChocolate, hasWhippedCream)+ "\n"+ "Thank you!";
    }


    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }




}