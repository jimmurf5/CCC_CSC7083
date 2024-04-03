package climateChangeCrisis;

import java.util.Comparator;

public class CompareByResources implements Comparator<Player>{
	

		@Override
		public int compare(Player o1, Player o2) {
			return o1.getResources()-o2.getResources();
	
	}

}
