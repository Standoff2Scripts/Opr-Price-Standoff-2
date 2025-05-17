String Token = "";  // Your Telegram bot token (Get it from @BotFather)
String chatId = ""; // Your Telegram Id (Get it from @myidbot)
float max = 0.03; // Maximum purchase price of a skin(WRITE YOUR PRICE)
String msg_start = ("🔥 The Opr Price script is up and running!" + "\n⏰ Start-up time:" + Time.getTime() + "\n📈 Maximum purchase price of a skin - " + max+ "G");

var 
    UppButoon = Point.get(777,999), // Button "Only my requests"
    UppButoon_Colour = 1,  // Colour of the "Only my requests"

    OkayButton = Point.get(777,999), // Button "ОК"
    OkayButton_Colour = 1, // Colour of the "ОК" button

    BuyButton = Point.get(777,999), // Button "Buy"
    AcceptButton = Point.get(777,999), // Button "Accept"

    leftPrice = Point.get(777,999), // Top left corner of the skin price
    rightPrice = Point.get(777,999), // Bottom right corner of the skin price

    BackButton = Point.get(777,999), // Button "Back"(X)
    BackButton_Colour = 1, // Button colour "Back"(X)

    PurchaseWindow = Point.get(777,999), // Popup window after buying a skin
    PurchaseWindow_Colour = 1, // The colour of the skin purchase popup window

    LeftName = Point.get(777,999), // Top left corner of the skin name
    RightName = Point.get(777,999), // Bottom right corner of the skin name

    LeftBalance = Point.get(777,999), // Top left corner of the balance
    RightBalance = Point.get(777,999); // Lower right corner of the balance



pfc.setSegMode(PSM_SINGLE_LINE);
pfc.startScreenCapture(2);
pfc.setOCRLang("eng");
pfc.sleep(20);
pfc.sendToTg(Token, chatId, msg_start);

pfc.log("The script started at:" + Time.getTime());
pfc.log(" ");


void click_UppButoon(){
    if(pfc.getColor(UppButoon) == UppButoon_Colour) {
        pfc.click(UppButoon);
        pfc.sleep(100);
    }
}

void exit_inspection_mode() {
    if(pfc.getColor(BackButton) == BackButton_Colour) {
        pfc.click(BackButton);
        pfc.sleep(200);
        pfc.click(UppButoon);
        pfc.sleep(30);
        pfc.click(UppButoon);
        pfc.sleep(150);
    }
}

void click_OkayButton() {
    if(pfc.getColor(OkayButton) == OkayButton_Colour) {
        pfc.click(OkayButton);
        pfc.sleep(80);
        pfc.click(UppButoon);
        pfc.sleep(30);
        pfc.click(UppButoon);
        pfc.sleep(150);
    }
}



while(!EXIT) {
    click_UppButoon();
    exit_inspection_mode();
    click_OkayButton();
    String lottText = pfc.getText(leftPrice,rightPrice);

    try {
    float lott = Float.parseFloat(lottText.trim());
    if(lott <= max) {
        pfc.click(BuyButton);
        pfc.sleep(40);
        pfc.click(AcceptButton);
        pfc.sleep(100);
        if(pfc.getColor(PurchaseWindow) == PurchaseWindow_Colour) {
            String lottText2 = pfc.getText(leftPrice,rightPrice);
            String SkinNameText = pfc.getText(LeftName,RightName);
            String BalanceText = pfc.getText(LeftBalance,RightBalance);
            String msg_skin_bought = ("💸 Skin has been caught" + lottText2 + "G" + "\n⏰ Time of purchase - " + Time.getTime() + "\n💭 Skin Name: \n" + SkinNameText + "\n🏦 Your Balance: " + BalanceText);
            pfc.sendToTg(Token, chatId, msg_skin_bought);
            pfc.sleep(1000);
            pfc.sleep(100);
            pfc.click(UppButoon);
            pfc.sleep(30);
            pfc.click(UppButoon);
            pfc.sleep(120);
        }  
        
    }

    } catch (NumberFormatException e) {
    } catch (Exception e) {
    }
}


