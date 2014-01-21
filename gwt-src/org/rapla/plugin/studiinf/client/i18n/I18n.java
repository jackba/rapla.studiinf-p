package org.rapla.plugin.studiinf.client.i18n;



import com.google.gwt.i18n.client.Messages;


public interface I18n extends Messages {
	String sendRequestToServer(int number);
	  String homeScreenTitle();
	  String otherLanguage();
	  String otherLanguageURL();
	  String courses();
	String rooms();
	String people();
	String pointsOfInterest();
	String courseSearchPage();
	String personSearchPage();
	String poiSearchPage();
	String roomSearchPage();
	String resultLabel();
	String frequentResultsLabel();
	String organigram();
	String homeButtonText();
	String qrInfoText();
	String courseOfStudy();
	String nextAppointments();
	String page();
	String freeRooms();
	String numberFormat(int numberValue);
	String noAppointments();
	String linkRapla();
	String extraInfos();
	String back();
	String freeUntil(String time);
	String nofreeRooms();
	String previous();
	String next();
	String information();
	}