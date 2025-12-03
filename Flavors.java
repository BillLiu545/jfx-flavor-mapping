
import javafx.collections.*;
import java.util.*;
import javafx.scene.chart.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.*;

public class Flavors
{
    private ObservableList<PieChart.Data> obsList = FXCollections.observableArrayList();
    private String[] flavorList =
    {
        "Sour",
        "Spicy",
        "Sweet",
        "Bitter",
        "Salty"
    };
    public boolean containsFlavor(String flavorName)
    {
        for (int i = 0; i < flavorList.length; i++)
        {
            if (flavorName.equals(flavorList[i]))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean includesFlavor(String flavorName)
    {
        Iterator<PieChart.Data> iter = obsList.iterator();
        while (iter.hasNext())
        {
            PieChart.Data next = iter.next();
            if (flavorName.equals(next.getName()))
            {
                return true;
            }
        }
        return false;
    }
    
    public ObservableList<PieChart.Data> getObsList()
    {
        return obsList;
    }
    
    public void add_input()
    {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Enter Flavor");
        nameDialog.setHeaderText("Enter Flavor");
        nameDialog.setContentText("Enter Flavor Type to add: ");
        Optional<String> nameOpt = nameDialog.showAndWait();
        String name = nameOpt.get();
        TextInputDialog numDialog = new TextInputDialog();
        numDialog.setTitle("Enter Flavor Quantity");
        numDialog.setHeaderText("Enter Flavor Quantity");
        numDialog.setContentText("Enter Flavor Quantity to be added: ");
        Optional<String> numOpt = numDialog.showAndWait();
        String num_str = numOpt.get();
        Integer num = null;
        try
        {
            num = Integer.parseInt(num_str);
        }
        catch (Exception ex)
        {
            num  = 0;
        }
        addFlavor(name, num);
    }
    
    public void addFlavor(String flavorName, int qty)
    {
        if (!containsFlavor(flavorName))
        {
            return;
        }
        if (includesFlavor(flavorName))
        {
            Iterator<PieChart.Data> iter = obsList.iterator();
            while (iter.hasNext())
            {
                PieChart.Data next = iter.next();
                if (flavorName.equals(next.getName()))
                {
                    double old_val = next.getPieValue();
                    next.setPieValue(old_val + qty);
                    break;
                }
            }
        }
        else
        {
            obsList.add(new PieChart.Data(flavorName, qty));
        }
    }
    
    public void remove_input()
    {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Enter Flavor");
        nameDialog.setHeaderText("Enter Flavor");
        nameDialog.setContentText("Enter Flavor Type to remove: ");
        Optional<String> nameOpt = nameDialog.showAndWait();
        String name = nameOpt.get();
        TextInputDialog numDialog = new TextInputDialog();
        numDialog.setTitle("Enter Flavor Quantity");
        numDialog.setHeaderText("Enter Flavor Quantity");
        numDialog.setContentText("Enter Flavor Quantity to be removed: ");
        Optional<String> numOpt = numDialog.showAndWait();
        String num_str = numOpt.get();
        Integer num = null;
        try
        {
            num = Integer.parseInt(num_str);
        }
        catch (Exception ex)
        {
            num  = 0;
        }
        removeFlavor(name, num);
    }
    
    public void removeFlavor(String flavorName, int qty)
    {
        if (includesFlavor(flavorName))
        {
            Iterator<PieChart.Data> iter = obsList.iterator();
            while (iter.hasNext())
            {
                PieChart.Data next = iter.next();
                if (flavorName.equals(next.getName()))
                {
                    double old_val = next.getPieValue();
                    double diff = old_val - qty;
                    next.setPieValue(diff);
                    if (diff <= 0)
                    {
                        obsList.remove(next);
                        break;
                    }
                }
            }
        }
    }
    
    public String getCompositions()
    {
        if (obsList.isEmpty())
        {
            return "None";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<PieChart.Data> iter = obsList.iterator();
        while (iter.hasNext())
        {
            PieChart.Data next = iter.next();
            sb.append(next.getName() + ": " + next.getPieValue() + "; ");
        }
        return sb.toString();
    }
}
