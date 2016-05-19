package com.bailian.ta.action;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bailian.ta.log.LogUtil;



public class FrameAction {
	private WebDriver driver;
	private BaseAction baseAction;
	private PageAction pageAction;
	private SwitchAction switchAction;
	private VerifyAction verifyAction;
	private KeyBoardAction keyBoardAction;
	public Finder finder;
	
	
	public FrameAction(WebDriver d){
		this.driver = d;
		baseAction = new BaseAction();
		pageAction = new PageAction(this.driver);
		switchAction = new SwitchAction(this.driver);
		verifyAction = new VerifyAction(this.driver);
		keyBoardAction = new KeyBoardAction(this.driver);
		finder = new Finder(this.driver);
	}
	
	public void open(String url, String description){
		try{
			pageAction.openPage(url);
			LogUtil.step("Open Page URL: " + url + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Open Page URL: " + url + ", Failed", description);
		}
	}
	
	public void refresh(String description){
		try{
			pageAction.refresh();
			LogUtil.step("Refresh Page, Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Refresh Page, Failed", description);
		}
	}
	
	public void click(By by, String description){
		try{
			baseAction.click(finder.find(by));
			LogUtil.step("Click " + by.toString() + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Click WebElement " + by.toString() + ", Failed", description);
		}
	}
	
	public void click(By by, int index, String description){
		try{
			baseAction.click(finder.find(by, index));
			LogUtil.step("Click " + by.toString() + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Click WebElement " + by.toString() + ", Failed", description);
		}
	}
	
	public void sendKeys(By by, String key, String description){
		try{
			baseAction.sendkeys(finder.find(by), key);
			LogUtil.step("Send Key '" + key + "' to WebElement " + by.toString() + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Send Key '" + key + "' to WebElement " + by.toString() + ", Failed", description);
		}
	}
	
	public void clearandsendKeys(By by, String key, String description){
		try{
			baseAction.clear(finder.find(by));
			baseAction.sendkeys(finder.find(by), key);
			LogUtil.step("Send Key '" + key + "' to WebElement " + by.toString() + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Send Key '" + key + "' to WebElement " + by.toString() + ", Failed", description);
		}
	}
	
	public void sendKeys(By by, String key, int index, String description){
		try{
			baseAction.sendkeys(finder.find(by, index), key);
			LogUtil.step("Send Key '" + key + "' to WebElement " + by.toString() + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Send Key '" + key + "' to WebElement " + by.toString() + ", Failed", description);
		}
	}
	
	public String getText(By by, String description){
		String val = "";
		try{
			val = baseAction.getText(finder.find(by));
			LogUtil.step("Get Text from WebElement " + by.toString() + ": " + val + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Get Text from WebElement " + by.toString() + ", Failed", description);
		}
		return val;
	}
	
	public String getText(By by, int index, String description){
		String val = "";
		try{
			val = baseAction.getText(finder.find(by, index));
			LogUtil.step("Get Text from WebElement " + by.toString() + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Get Text from WebElement " + by.toString() + ", Failed", description);
		}
		return val;
	}
	
	public void clear(By by, String description){
		try{
			baseAction.clear(finder.find(by));
			LogUtil.step("Clear Text " + by.toString() + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Clear WebElement " + by.toString() + ", Failed", description);
		}
	}
	
	public void clear(By by, int index, String description){
		try{
			baseAction.clear(finder.find(by, index));
			LogUtil.step("Clear Text " + by.toString() + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Clear WebElement " + by.toString() + ", Failed", description);
		}
	}
	
	public void submit(By by, String description){
		try{
			baseAction.submit(finder.find(by));
			LogUtil.step("Submit " + by.toString() + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Submit WebElement " + by.toString() + ", Failed", description);
		}
	}
	
	public void submit(By by, int index, String description){
		try{
			baseAction.submit(finder.find(by, index));
			LogUtil.step("Submit " + by.toString() + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Submit WebElement " + by.toString() + ", Failed", description);
		}
	}
	
	public String getAttribute(By by, String attribute, String description){
		String att = "";
		try{
			att = baseAction.getAttribute(finder.find(by), attribute);
			LogUtil.step("Get WebElement Attribute " + attribute + " " + by.toString() + ", Passed", description);
			return att;
		}
		catch(Exception e){
			FrameAssertion.fail("Get WebElement Attribute " + attribute + " " + by.toString() + ", Failed", description);
			return att;
		}
	}
	
	public String getCss(By by, String css, String description){
		String val = "";
		try{
			val = baseAction.getCssVaule(finder.find(by), css);
			LogUtil.step("Get WebElement Css Value " + css + " " + by.toString() + ", Passed", description);
			return val;
		}
		catch(Exception e){
			FrameAssertion.fail("Get WebElement Css Value " + css + " " + by.toString() + ", Failed", description);
			return val;
		}
	}
	
	public String getTag(By by, String description){
		String val = "";
		try{
			val = baseAction.getTagName(finder.find(by));
			LogUtil.step("Get WebElement Tage Value " + by.toString() + ", Passed", description);
			return val;
		}
		catch(Exception e){
			FrameAssertion.fail("Get WebElement Tage Value " + by.toString() + ", Failed", description);
			return val;
		}
	}
	
	public boolean verifyExist(By by, String description) {
		if (finder.isExist(by)) {
			LogUtil.step("Check availibility of " + by.toString() + ": Exist", description);
			return true;
		} else {
			LogUtil.step("Check availibility of " + by.toString() + ": Not exist", description);
			return false;
		}
	}

	public boolean verifyDisplay(By by, String description){
		boolean val = false;
		try{
			val = baseAction.isDisplayed(finder.find(by));
			LogUtil.step("Is Display " + by.toString() + ", Passed", description);
			return val;
		}
		catch(Exception e){
			FrameAssertion.fail("Is Display " + by.toString() + ", Failed", description);
			return val;
		}
	}
	
	public boolean verifyDisplay(By by, int index, String description){
		boolean val = false;
		try{
			val = baseAction.isDisplayed(finder.find(by, index));
			LogUtil.step("Is Display " + by.toString() + ", Passed", description);
			return val;
		}
		catch(Exception e){
			FrameAssertion.fail("Is Display " + by.toString() + ", Failed", description);
			return val;
		}
	}
	
	public boolean verifySelect(By by, String description){
		boolean val = false;
		try {
			val = baseAction.isSelected(finder.find(by));
			LogUtil.step("Is Select " + by.toString() + ", Passed", description);
			return val;
		} catch (Exception e) {
			FrameAssertion.fail("Is Select " + by.toString() + ", Failed", description);
			return val;
		}
	}
	
	public boolean verifySelect(By by, int index, String description){
		boolean val = false;
		try {
			val = baseAction.isSelected(finder.find(by, index));
			LogUtil.step("Is Select " + by.toString() + ", Passed", description);
			return val;
		} catch (Exception e) {
			FrameAssertion.fail("Is Select " + by.toString() + ", Failed", description);
			return val;
		}
	}
	
	public boolean verifyEnable(By by, String description){
		boolean val = false;
		try {
			val = baseAction.isEnabled(finder.find(by));
			LogUtil.step("Is Enabled " + by.toString() + ", Passed", description);
			return val;
		} catch (Exception e) {
			FrameAssertion.fail("Is Enabled " + by.toString() + ", Failed", description);
			return val;
		}
	}
	
	public boolean verifyEnable(By by, int index, String description){
		boolean val = false;
		try {
			val = baseAction.isEnabled(finder.find(by, index));
			LogUtil.step("Is Enabled " + by.toString() + ", Passed", description);
			return val;
		} catch (Exception e) {
			FrameAssertion.fail("Is Enabled " + by.toString() + ", Failed", description);
			return val;
		}
	}
	
	public void removeReadOnly(By by, String description){
		try {
			pageAction.removeReadOnly(finder.find(by));
			LogUtil.step("Remove Read Only: " + by.toString() + ", Passed", description);
		} catch (Exception e) {
			FrameAssertion.fail("Remove Read Only : " + by.toString() + ", Failed", description);
		}
	}
	
	public void removeReadOnly(String id, String description){
		try {
			pageAction.removeReadOnly(id);
			LogUtil.step("Remove Read Only: by." + id + ", Passed", description);
		} catch (Exception e) {
			FrameAssertion.fail("Remove Read Only by Id: " + id + ", Failed", description);
		}
	}
	
	public void alertAccept(String description){
		try {
			pageAction.alertAccept();
			LogUtil.step("Accept Alert, Passed", description);
		} catch (Exception e) {
			FrameAssertion.fail("Accept alert, Failed", description);
		}
	}
	
	public void alertCancel(String description){
		try {
			pageAction.alertCancel();
			LogUtil.step("Cancle Alert: Succeed, Passed", description);
		} catch (Exception e) {
			FrameAssertion.fail("Cancel alert, Failed", description);
		}
	}
	
	public void alertSendKey(String key, String description){
		try {
			pageAction.alertSendkey(key);
			LogUtil.step("Send Alert Text: " + key + ", Passed", description);
		} catch (Exception e) {
			FrameAssertion.fail("Send key '" + key + "' to alert, Failed", description);
		}
	}
	
	public String alertGetText(String description){
		String val = "";
		try {
			val = pageAction.alertGetText();
			LogUtil.step("Get Alert Text: {" + val + ", Passed}", description);
			return val;
		} catch (Exception e) {
			FrameAssertion.fail("Get Text from alert, Failed", description);
			return val;
		}
	}
	
	public void switchDefault(String description){
		try {
			switchAction.frameSwitchDefault();
			LogUtil.step("Switch to Default Content, Passed", description);
		} catch (Exception e) {
			FrameAssertion.fail("Switch Defalut Content, Failed", description);
		}
	}
	
	public void switchFrame(By by, String description){
		try {
			switchAction.frameSwitch(by);
			LogUtil.step("Switch to Frame: " + by.toString() + ", Passed", description);
		} catch (Exception e) {
			FrameAssertion.fail("Switch Frame " + by.toString() + ", Failed", description);
		}
	}
	
	public void switchFrameParent(String description){
		try {
			switchAction.frameSwitchParent();
			LogUtil.step("Switch to Parent Frame, Passed", description);
		} catch (Exception e) {
			FrameAssertion.fail("Switch Parent Frame, Failed", description);
		}
	}
	
	public void switchPage(String description){
		try{
			switchAction.pageSwitchByHandle(description);
		}
		catch(Exception e){
			FrameAssertion.fail("Switch Window, Failed", description);
		}
	}
	
	public int getElementNum(By by, String description){
		int val = 0;
		try{
			val = finder.finds(by).size();
			LogUtil.step("Found " + val + " WebElements " + by.toString(), description);
		}
		catch(Exception e){
			FrameAssertion.fail("Found WebElements " + by.toString() + ", Failed", description);
		}
		return val;
	}
	
	public boolean verfiyLoad(By by, String description){
		boolean val = false;
		try {
			verifyAction.verifyLoad(by);
			val = true;
			LogUtil.step("Verify Loaded " + by.toString() + ", Passed", description);
			return val;
		} catch (Exception e) {
			FrameAssertion.fail("Verify Loaded " + by.toString() + ", Failed", description);
			return val;
		}
	}
	
	public boolean isLoad(By by, String description){
		boolean val = false;
		try {
			verifyAction.verifyLoad(by);
			val = true;
			LogUtil.step("Is Loaded " + by.toString() + ", " + val + ", Passed", description);
			return val;
		} catch (Exception e) {
			LogUtil.step("Is Loaded " + by.toString() + ", " + val + ", Failed", description);
			return val;
		}
	}
	
	
	public boolean verifyDisappear(By by, String description){
		boolean val = false;
		try {
			verifyAction.verifyDisappear(by);
			val = true;
			LogUtil.step("Verify Disappear " + by.toString() + ", Passed", description);
			return val;
		} catch (Exception e) {
			FrameAssertion.fail("Verify Disappear " + by.toString() + ", Failed", description);
			return val;
		}
	}
	
	public int getTableSize(By by, String description){
		int val = 0;
		try{
			val = finder.find(by).findElements(By.tagName("tr")).size() - 1;
			LogUtil.step("The table " + by.toString() + " size: " + val + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("The table " + by.toString() + " size: " + val + ", Failed", description);
		}
		return val;
	}
	
	/****************************************************************************************************************************************
	 * @param by
	 * @param row
	 * @param col
	 * @param description
	 * @return Data in cell of the table
	 * @author ink
	 ***************************************************************************************************************************************/
	
	public String getTableCell(By by, int row, int col, String description){
		String val = "";
		try{
			val = finder.find(by).findElements(By.tagName("tr")).get(row).findElements(By.tagName("td")).get(col - 1)
					.getText();
			LogUtil.step("The data in table " + by.toString() + " Row: " + row + ", Col: " + (col - 1) + " is " + val
					+ ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail(
					"The data in table " + by.toString() + " Row: " + row + ", Col: " + (col - 1) + ", Failed",
					description);
		}
		return val;
	}
	
	public void selectText(By by, String text, String description){
		try{
			baseAction.selectText(finder.find(by), text);
			LogUtil.step("Select " + by.toString() + " text: " + text + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Select " + by.toString() + " text: " + text + ", Failed", description);
		}
	}
	
	public void selectIndex(By by, int index, String description){
		try{
			baseAction.selectIndex(finder.find(by), index);
			LogUtil.step("Select " + by.toString() + " index: " + index + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Select " + by.toString() + " index: " + index + ", Failed", description);
		}
	}
	
	public void selectValue(By by, String value, String description){
		try{
			baseAction.selectValue(finder.find(by), value);
			LogUtil.step("Select " + by.toString() + " value: " + value + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Select " + by.toString() + " value: " + value + ", Failed", description);
		}
	}
	
	public List<String> getSelectOptions(By by, String description){
		List<String> val = null;
		try{
			val = baseAction.getSelectOptions(finder.find(by));
			LogUtil.step("Get select options " + by.toString() + ", Passed", description);
		}
		catch(Exception e){
			FrameAssertion.fail("Get select options " + by.toString() + ", Failed", description);
		}
		return val;
	}
	
	public void keyPressEnter(By by, String description){
		try{
			LogUtil.step("Press key Enter " + by.toString());
			keyBoardAction.pressKeyEnter(finder.find(by));
		}
		catch(Exception e){
			FrameAssertion.fail("Press key Enter "+ by.toString() +" Failed", description);
		}
	}
	
	public void keyPressEnter(){
		try{
			LogUtil.step("Press key Enter");
			keyBoardAction.pressKeyEnter();
		}
		catch(Exception e){
			FrameAssertion.fail("Press key Enter Failed");
		}
	}
	
	public void sleep(int sleep, String description){
		try{
			LogUtil.step("System will stop " + sleep + " Seconds", description);
			Thread.sleep(sleep * 1000); 
		}
		catch(Exception e){
			FrameAssertion.fail("System stop " + sleep + " Seconds, Failed", description);
		}
	}
	
	public void tearDown(String description){
		try{
			pageAction.tearDown();
			LogUtil.frameLog("Delete Cookies and Quit");
		}
		catch(Exception e){
			FrameAssertion.fail("Delete Cookies and Quit, Failed", description);
		}
	}
	

}
