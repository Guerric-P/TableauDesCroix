package DAL;

public class DacBase {
	public void TraiterErreur(Exception e){
		System.out.println(e.toString());
		e.printStackTrace();
	}
}
