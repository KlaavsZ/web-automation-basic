package pages.pageLib;

import com.codeborne.selenide.SelenideElement;
import pages.PageManager;
import io.cucumber.datatable.DataTable;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectAddressPage extends BasePage {
  // Constructor
  public SelectAddressPage(){
    addElement("addAddress", "button[aria-label='Add a new address']");
    addElement("radioButton", "mat-radio-button[class*=mat-radio-button]");
    addElement("continue", "button[aria-label='Proceed to payment selection']");
    addElement("timTester", "//mat-row[contains(., 'Tim Tester')]//mat-radio-button");
    addElement("country", "input[placeholder='Please provide a country.']");
    addElement("name", "input[placeholder='Please provide a name.']");
    addElement("mobileNumber", "input[placeholder='Please provide a mobile number.']");
    addElement("zipCode", "input[placeholder='Please provide a ZIP code.']");
    addElement("address", "textarea[placeholder='Please provide an address.']");
    addElement("city", "input[placeholder='Please provide a city.']");
    addElement("state", "input[placeholder='Please provide a state.']");
    addElement("submit", "button[id='submitButton']");
  }
  // Methods
  @Override
  public SelenideElement getMainElement() {
    return getElement("addAddress");
  }
  @Override
  public void navigate(String element) {
    switch (element){
      case "continue": PageManager.setCurrentPage(PageManager.getDeliveryPage()); break;
      default        : super.navigate(element);
    }
  }

    public void doAction(String action, DataTable dataTable) {
    switch(capitalizeSecond(action)){
      case "validateAddress": validateAddress(dataTable); break;
      default: super.doAction(action, dataTable);
    }
  }

  public void validateAddress(DataTable dataTable){
    Map<String,String> data = dataTable.transpose().asMaps().get(0);
    info("Validating Address: " + data);

    if(data.get("name") != null){
      assertThat(getElement("name").text().contains(data.get("name"))).isTrue();
    }

    if(data.get("address") != null){
      assertThat(getElement("address").text().contains(data.get("address"))).isTrue();
    }

    if(data.get("country") != null){
      assertThat(getElement("country").text().contains(data.get("country"))).isTrue();
    }
  }


}
