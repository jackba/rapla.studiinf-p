package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.pages.AbstractDetailPage;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.ui.FontIcon.Position;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * Button to navigate between different pages
 *
 */
public class NavButton extends Composite implements NavigationButtonSpec, HasText {

	private static NavButtonUiBinder uiBinder = GWT
			.create(NavButtonUiBinder.class);

	interface NavButtonUiBinder extends UiBinder<Widget, NavButton> {
	}
	interface NavButtonStyle extends CssResource{
		String navigationButton();	
		String tile();
		String twoLines();
		String disabled();
		String mobile();
	}
	AbstractPage targetPage;
	String targetId;
	int numberValue = 0;
	FontIcon fontIcon;
	FontIcon.Position fontPosition;
	String text;
	boolean showWhenDisabled = true;
	boolean enabled = true;
	boolean twoLines = false;
	public boolean isTwoLines() {
		return twoLines;
	}

	public void setTwoLines(boolean twoLines) {
		this.twoLines = twoLines;
		if(twoLines){
			textLabel.addClassName(style.twoLines());
		}else{
			textLabel.removeClassName(style.twoLines());
		}
	}

	ClickHandler clickHandler;

	public NavButton(String text,AbstractPage targetPage,String targetId) {
		initWidget(uiBinder.createAndBindUi(this));
		setTargetPage(targetPage);
		setTargetId(targetId);
		setText(text);
		button.addStyleName("navigationButton");
		number.addClassName("number");
		icon.addClassName("icon");
		textLabel.addClassName("text");
		shadow.addClassName("shadow");
	}
	
	public NavButton(FontIcon fontIcon, String text,AbstractPage targetPage,String targetId){
		this(text,targetPage, targetId);
		setIcon(fontIcon);
	}
	
	public NavButton(FontIcon fontIcon, FontIcon.Position fontPosition, String text,AbstractPage targetPage,String targetId){
		this(text,targetPage, targetId);
		setIcon(fontIcon,fontPosition);
	}
	public NavButton(int number, FontIcon fontIcon,String text, AbstractPage targetPage,String targetId){
		this(fontIcon,text,targetPage,targetId);
		setNumber(number);
	}
	
	@UiField
	Button button;
	
	@UiField
	SpanElement number;
	
	@UiField
	SpanElement icon;
		
	@UiField
	SpanElement iconRight;
	
	@UiField
	SpanElement textLabel;
	
	@UiField
	NavButtonStyle style;
	
	@UiField
	DivElement shadow;
	
	
	@UiHandler("button")
	void onClick( ClickEvent e){
		handlePressEvent();
		button.setFocus(false);
	}
	
	protected void handlePressEvent(){
		if(getEnabled()){
			if(getClickHandler() == null){
				if(targetId == null){
					Navigation.goToPage(targetPage);
				}else{
					Navigation.goToPage(targetPage, targetId);
				}
			}else{
				getClickHandler().onClick( null);
			}
			
		}
	}
	

	@Override
	public String getTargetId() {
		return targetId;
	}

	@Override
	public void setTargetId(String targetId) {
		this.targetId = targetId;
		updateEnabledState();
	}

	@Override
	public int getNumber() {
		return numberValue;
	}

	@Override
	public void setNumber(int numberValue) {
		this.numberValue = numberValue;
		if(numberValue > 0){
			number.setInnerHTML(Studiinf.i18n.numberFormat(numberValue));
		}else{
			number.setInnerHTML("");
		}
	}

	@Override
	public FontIcon getIcon() {
		return fontIcon;
	}

	@Override
	public void setIcon(FontIcon fontIcon) {
		setIcon(fontIcon, this.fontPosition);
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
		if(text != null){
			textLabel.setInnerSafeHtml(SafeHtmlUtils.fromString(text));
		}else{
			textLabel.setInnerHTML("");
		}
	}

	@Override
	public AbstractPage getTargetPage() {
		return targetPage;
	}

	@Override
	public void setTargetPage(AbstractPage targetPage) {
		this.targetPage = targetPage;
		updateEnabledState();
	}

	@Override
	public void setSize(double viewPortSize) {
		this.getElement().getStyle().setProperty("fontSize", viewPortSize+"vh");		
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		updateEnabledState();
	}

	@Override
	public boolean getEnabled() {
		boolean result = enabled;
		if(getClickHandler() == null){
			if(targetPage == null){
				result = false;
			}else{
				try {
					AbstractDetailPage d = (AbstractDetailPage) targetPage;
					if(d.getHistoryKey() == null || targetId == null){
						result = false;
					}
				} catch (Exception e) {
					result = enabled;
				}
			}
		}else{
			result = enabled;
		}
		return result;
	}
	
	private void updateEnabledState(){
		if(getEnabled()){
			//this.getElement().removeAttribute("disabled");
			button.removeStyleName(style.disabled());
			this.getElement().getStyle().setVisibility(Visibility.VISIBLE);
		}else{
			//this.getElement().setAttribute("disabled","disabled");
			button.addStyleName(style.disabled());
			if(this.showWhenDisabled){
				this.getElement().getStyle().setVisibility(Visibility.VISIBLE);
			}else{
				this.getElement().getStyle().setVisibility(Visibility.HIDDEN);
			}
		}
	}

	public void setClickHandler(ClickHandler clickHandler) {
				this.clickHandler = clickHandler;
				updateEnabledState();
	}
	public ClickHandler getClickHandler(){
		return clickHandler;
	}
	
	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
		this.addStyleName(this.style.navigationButton());
	}

	@Override
	public void setIcon(FontIcon fontIcon, Position position) {
		this.fontIcon = fontIcon;
		if(position == null){
			position = FontIcon.Position.LEFT;
		}
		this.fontPosition = position;
		icon.setInnerHTML("");
		iconRight.setInnerHTML("");
		if(fontIcon != null && fontIcon.getUrl().startsWith("icon-")){
			if(position == FontIcon.Position.LEFT || position == FontIcon.Position.BOTH){
				icon.setInnerHTML("<span class='"+fontIcon.getUrl()+"'></span>");
			}
			if(position == FontIcon.Position.RIGHT || position == FontIcon.Position.BOTH){
				iconRight.setInnerHTML("<span class='"+fontIcon.getUrl()+"'></span>");
			}
		}
	}

	@Override
	public void setShowWhenDisabled(boolean enabled) {
		this.showWhenDisabled = enabled;
		updateEnabledState();
	}

	@Override
	public boolean getShowWhenDisabled() {
		return this.showWhenDisabled;
	}
	

}
