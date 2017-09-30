// A class to hold the policy information

package policymanager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// AJ Fisher
class Policy
{
    // a list of class attributes
    private String clientName;
    private String referenceNumber;
    private int gadgetNumber;
    private double gadgetValue;
    private double excess;
    private String paymentTerm;
    private double premium;
    private String date;
    
    //an empty constraint
    Policy()
    {
        
    }
    
    //a constraint containing all attributes
    Policy (String n, String r, int q, double v, double e, String t, String d )
    {
        clientName = n;
        referenceNumber = r;
        gadgetNumber = q;
        gadgetValue = v;
        excess = e;
        paymentTerm = t;
        date = d;
        
    }
    
    // getters for the attributes, so the values can be used in the main code
    String getName()
    {
        return clientName;
    }
    String getRef()
    {
        return referenceNumber;
    }
    int getQuantity()
    {
        return gadgetNumber;
    }
    double getValue()
    {
        return gadgetValue;
    }
    double getExcess()
    {
        return excess;
    }
    String getTerms()
    {
        return paymentTerm;
    }
    double getPremium()
    {
        return premium;
    }
    String getDate()
    {
        return date;
    }
    
    // setters for the attributes, to assign values
    void setName(String name)
    {
        clientName = name;          
    }
    void setRef(String ref)
    {
        referenceNumber = ref;
    }
    void setQuantity(int quantity)
    {
        gadgetNumber = quantity;
    }
    void setValue(double value)
    {
        gadgetValue = value;
    }
    void setExcess(double ex)
    {
        excess = ex;
    }
    void setTerm(String t)
    {
        paymentTerm = t;
    }
    void setPremium()
    {
        // calculates the monthly premium using entered values
        // checks the gadget value and number of items to find the relevent premium
        if (gadgetValue <=550)
        {
            if (gadgetNumber == 1)
            {
                premium = 4.99;
            }
            else if (gadgetNumber >= 2 && gadgetNumber <=3)
            {
                premium = 9.99;
            }
            else if (gadgetNumber >= 4 && gadgetNumber <=5 )
            {
                premium = 14.99;
            }
        }
        
        else if (gadgetValue > 550 && gadgetValue <= 800)
        {
           if (gadgetNumber == 1)
            {
                premium = 6.15;
            }
            else if (gadgetNumber >= 2 && gadgetNumber <=3)
            {
                premium = 12.35;
            }
            else if (gadgetNumber >= 4 && gadgetNumber <=5 )
            {
                premium = 18.60;
            } 
        }
        else if (gadgetValue >=800 && gadgetValue <=1000)
        {
            if (gadgetNumber == 1)
            {
                premium = 7.30;
            }
            else if (gadgetNumber >= 2 && gadgetNumber <=3)
            {
                premium = 14.55;
            }
            else if (gadgetNumber >= 4 && gadgetNumber <=5 )
            {
                premium = 21.82;
            }
        }
        
        // calculates the discounted premium if required
        if ( excess == 70.0 )
        {
            premium = premium * 0.8;
        }
        else if (excess == 60.0 )
        {
            premium = premium * 0.85;
        }
        else if (excess == 50.0 )
        {
            premium = premium * 0.9;
        }
        else if (excess ==40.0)
        {
            premium = premium * 0.95;
        }
        
        
        // calculates the annual premium if required
        if (paymentTerm.equals("Annual"))
        {
            premium = premium * 0.9;
            premium = premium * 12;
        }
    }
    void setDate()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        date = sdf.format(cal.getTime());
    }
           
    
}