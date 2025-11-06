package Model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class emp implements Serializable{
	 @Id
	 private int EMPNO;
	 private String ENAME;
	 private String JOB;
	 private int MGR;
	 private float SAL;
	 private float COMM;
	 private int DEPTNO;
	 public emp() {
		super();
		// TODO Auto-generated constructor stub
	 }
	 public emp(int eMPNO, String eNAME, String jOB, int mGR, float sAL, float cOMM, int dEPTNO) {
		super();
		EMPNO = eMPNO;
		ENAME = eNAME;
		JOB = jOB;
		MGR = mGR;
		SAL = sAL;
		COMM = cOMM;
		DEPTNO = dEPTNO;
	 }
	 public int getEMPNO() {
		 return EMPNO;
	 }
	 public void setEMPNO(int eMPNO) {
		 EMPNO = eMPNO;
	 }
	 public String getENAME() {
		 return ENAME;
	 }
	 public void setENAME(String eNAME) {
		 ENAME = eNAME;
	 }
	 public String getJOB() {
		 return JOB;
	 }
	 public void setJOB(String jOB) {
		 JOB = jOB;
	 }
	 public int getMGR() {
		 return MGR;
	 }
	 public void setMGR(int mGR) {
		 MGR = mGR;
	 }
	 public float getSAL() {
		 return SAL;
	 }
	 public void setSAL(float sAL) {
		 SAL = sAL;
	 }
	 public float getCOMM() {
		 return COMM;
	 }
	 public void setCOMM(float cOMM) {
		 COMM = cOMM;
	 }
	 public int getDEPTNO() {
		 return DEPTNO;
	 }
	 public void setDEPTNO(int dEPTNO) {
		 DEPTNO = dEPTNO;
	 }
	 @Override
	 public int hashCode() {
		return Objects.hash(COMM, DEPTNO, EMPNO, ENAME, JOB, MGR, SAL);
	 }
	 @Override
	 public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		emp other = (emp) obj;
		return Float.floatToIntBits(COMM) == Float.floatToIntBits(other.COMM) && DEPTNO == other.DEPTNO
				&& EMPNO == other.EMPNO && Objects.equals(ENAME, other.ENAME) && Objects.equals(JOB, other.JOB)
				&& MGR == other.MGR && Float.floatToIntBits(SAL) == Float.floatToIntBits(other.SAL);
	 }
	 @Override
	 public String toString() {
		return "emp [EMPNO=" + EMPNO + ", ENAME=" + ENAME + ", JOB=" + JOB + ", MGR=" + MGR + ", SAL=" + SAL + ", COMM="
				+ COMM + ", DEPTNO=" + DEPTNO + "]";
	 }
}
