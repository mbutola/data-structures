import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> fileNames = new ArrayList<String>(
											Arrays.asList(new String[]{
													"fileOneName",
													"fileTwoName"}
											)
										);
		
		Predicate<String> p = in -> in.contains("One");
		
		Predicate<String> p1 = new Predicate<String>() {
			@Override
			public boolean test(String t) {
				// TODO Auto-generated method stub
				return t.contains("One");
			}	
		};
		
		fileNames.stream()
					.filter(file -> file.contains("One"))
					.forEach(System.out::println);

		fileNames.stream()
					.filter(p1)
					.forEach(System.out::println);
		
		
	}
	
	

}
