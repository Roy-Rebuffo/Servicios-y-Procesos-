package Model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class dept implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int DEPTNO;
	private String DNAME;
	private String LOC;
	
	public dept() {
		super();
		// TODO Auto-generated constructor stub
	}

	public dept(int dEPTNO, String dNAME, String lOC) {
		super();
		DEPTNO = dEPTNO;
		DNAME = dNAME;
		LOC = lOC;
	}

	public int getDEPTNO() {
		return DEPTNO;
	}

	public void setDEPTNO(int dEPTNO) {
		DEPTNO = dEPTNO;
	}

	public String getDNAME() {
		return DNAME;
	}

	public void setDNAME(String dNAME) {
		DNAME = dNAME;
	}

	public String getLOC() {
		return LOC;
	}

	public void setLOC(String lOC) {
		LOC = lOC;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(DEPTNO, DNAME, LOC);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		dept other = (dept) obj;
		return DEPTNO == other.DEPTNO && Objects.equals(DNAME, other.DNAME) && Objects.equals(LOC, other.LOC);
	}

	@Override
	public String toString() {
		return "Departamentos [DEPTNO=" + DEPTNO + ", DNAME=" + DNAME + ", LOC=" + LOC + "]";
	}
	
}
