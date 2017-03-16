package innerClass;

public class Outer {

	private int outerInt = 1;

	public class inner {

		public int innerInt = 1;

		protected void setInt() {

			innerInt++;
			System.out.println("innerInt = " + innerInt);
		}

	}

}
