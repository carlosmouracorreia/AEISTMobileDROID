package pt.aeist.mobile.eventos;

import java.util.ArrayList;
import java.util.List;

public class EventoContainer {
	private String eventoNome;
	private String eventoDesc;
	private String eventoLink;
	private String imageLink;
	private List<String> more;
	private String date;
	
	public EventoContainer() {
		more = new ArrayList<String>();
	}
	/**
	 * @return the eventoNome
	 */
	public void addMisc(String a) {
		more.add(a);
	}
	
	public List<String> getMisc() {
		return more;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String _date) {
		date = _date;
	}
	
	public String getMisc(int arg0) {
		return more.get(arg0);
	}
	
	public String getEventoNome() {
		return eventoNome;
	}
	/**
	 * @param eventoNome the eventoNome to set
	 */
	public void setEventoNome(String eventoNome) {
		this.eventoNome = eventoNome;
	}
	/**
	 * @return the eventoDesc
	 */
	public String getEventoDesc() {
		return eventoDesc;
	}
	/**
	 * @param eventoDesc the eventoDesc to set
	 */
	public void setEventoDesc(String eventoDesc) {
		this.eventoDesc = eventoDesc;
	}
	/**
	 * @return the eventoLink
	 */
	public String getEventoLink() {
		return eventoLink;
	}
	/**
	 * @param eventoLink the eventoLink to set
	 */
	public void setEventoLink(String eventoLink) {
		this.eventoLink = eventoLink;
	}
	/**
	 * @return the imageLink
	 */
	public String getImageLink() {
		return imageLink;
	}
	/**
	 * @param imageLink the imageLink to set
	 */
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
	
	
}
