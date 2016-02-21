package harbor;

import java.util.ArrayList;
import java.util.List;

public class Harbor {
	
	int sectionDefaultHeight;
	List<Section> warehouse;
	
	public Harbor(int sectionDefaultHeight) {
		warehouse = new ArrayList<Section>();
		this.sectionDefaultHeight = sectionDefaultHeight;
		Section section = new Section(sectionDefaultHeight);
		warehouse.add(section);
	}
	
	public void receiveContainer(Container container) {
		boolean success = false;
		for(Section section : warehouse) {
			if( success = section.addContainer(container) ) {
				return;
			} 
		}
		if(!success) {
			Section section = new Section(sectionDefaultHeight);
			section.addContainer(container);
			warehouse.add(section);
		}
	}
	
	public void receiveAllContainers(List<Container> containers) {
		for(Container c : containers) {
			receiveContainer(c);
		}
	}
	
	public void setSectionDefaultHeight(int height) {
		sectionDefaultHeight = height;
	}
	
	public void printHarborContent() {
		int counter = 1;
		for(Section section : warehouse) {
			System.out.println("Section " + counter);
			for(Container c : section
					.getContainers()) {
				System.out.println(c);
			}
			System.out.println();
			counter++;
		}
	}
}
