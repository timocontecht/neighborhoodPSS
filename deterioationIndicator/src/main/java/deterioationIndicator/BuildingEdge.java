package deterioationIndicator;

import org.visico.neighborhoodpss.domain.project.BuildingDataTypeDTO;

public class BuildingEdge {
	BuildingNode start;
	BuildingNode end;
	
	public BuildingEdge (BuildingNode start, BuildingNode end)  {
		this.start = start;
		this.end = end;
	}

	public BuildingNode getStart() {
		return start;
	}

	public BuildingNode getEnd() {
		return end;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 31 * (start !=null ? start.hashCode() : 0);
		hashCode *= (end !=null ? end.hashCode() : 0);
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
	        return false;
     	if (obj == this)
            return true;
        if (!(obj instanceof BuildingEdge))
            return false;
        
        BuildingEdge edge = (BuildingEdge) obj;
        
        if (edge.start.equals(this.start) && edge.end.equals(this.end))
        	return true;
        else if (edge.start.equals(this.end) && edge.end.equals(this.start))
        	return true;
        else
        	return false; 
	}
}
