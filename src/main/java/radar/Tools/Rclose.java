package radar.Tools;

import java.io.IOException;

public class Rclose {

	public static void Rclose() {
		// TODO Auto-generated method stub
		String processName="Rserve.exe";
		try {
			Runtime.getRuntime().exec("taskkill /im " + processName + " /f");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("false");
			e.printStackTrace();
		}
	}

}
