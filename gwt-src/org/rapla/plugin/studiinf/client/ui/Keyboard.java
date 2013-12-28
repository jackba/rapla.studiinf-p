package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Keyboard extends FlowPanel implements ClickHandler {
	private final TextBox inputField;
	
	public Keyboard(TextBox inputField) {
		super();
		this.inputField = inputField;
					
		addStringAsRow("Q|W|E|R|T|Z|U|I|O|P|Ü|return");
		addStringAsRow("A|S|D|F|G|H|J|K|L|Ö|Ä");
		addStringAsRow("Y|X|C|V|B|N|M|.|search");
		addStringAsRow(" ");
		
	}

	@Override
	public void onClick(ClickEvent event) {
		try {
			Button key = (Button) event.getSource();
			inputField.setText(inputField.getText() + key.getText());
			char keyChar =  key.getText().charAt(0);
			
			DomEvent.fireNativeEvent(
					Document.get().createKeyUpEvent(
							false, false, false, false,keyChar)
							, inputField);

					
		} catch (Exception e) {
			
		}
			
	}
	
	public void addStringAsRow(String keys){
		FlowPanel row = new FlowPanel();
		Button button;
		row.addStyleName("keyboardRow");
		String[] keysArray = keys.split("\\|");
		for(String key : keysArray){
			if(key.length() == 1){
			button = new Button(key);
			
			button.addClickHandler(this);
			}else{
				button = handleSpecialKey(key);
				
			}
			button.getElement().getStyle().setWidth(100/keysArray.length, Unit.PCT);
			button.getElement().getStyle().setProperty("height", "3.5vh");
			button.getElement().getStyle().setProperty("fontSize", "2.5vh");
			button.getElement().getStyle().setProperty("padding", "0px");
			row.add(button);
		}
		this.add(row);
		if(this.getChildren().size() == 2){
			row.getElement().getStyle().setMarginLeft(50/keysArray.length, Unit.PCT);
		}else if(this.getChildren().size() <= 3){
			row.getElement().getStyle().setMarginRight(50/keysArray.length, Unit.PCT);
		}
		row.getElement().getStyle().setProperty("heigth", "3.75vh");
	}

	private Button handleSpecialKey(String key) {
		Button button = null;
		if(key.equals("return")){
			button = new Button("<i class='fa fa-arrow-left'></i>");
			button.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					inputField.setText(inputField.getText().substring(0, inputField.getText().length() - 1));
					DomEvent.fireNativeEvent(
							Document.get().createKeyUpEvent(
									false, false, false, false,0)
									, inputField);
				}
			});
		}else if(key.equals("search")){
			button = new Button("<i class='fa fa-search'></i>");
			button.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					DomEvent.fireNativeEvent(
							Document.get().createKeyUpEvent(
									false, false, false, false,0)
									, inputField);
				}
			});
		}
		return button;
	}

	
}
