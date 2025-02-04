abstract class Examination {
    public abstract double evaluateMarks(double marks);
    public abstract double evaluateMarks(double marks, int subjectCount);
    public abstract String evaluateMarks(double marks, int subjectCount, double passMarks);
    public abstract double evaluateMarks(double theoryMarks, double practicalMarks, double theoryWeight, double practicalWeight);
}

class ConcreteExamination extends Examination {
    @Override
    public double evaluateMarks(double marks) {
        return marks;
    }

    @Override
    public double evaluateMarks(double marks, int subjectCount) {
        return (marks / subjectCount);
    }

    @Override
    public String evaluateMarks(double marks, int subjectCount, double passMarks) {
        double percentage = (marks / subjectCount);
        return percentage >= passMarks ? "Pass" : "Fail";
    }

    @Override
    public double evaluateMarks(double theoryMarks, double practicalMarks, double theoryWeight, double practicalWeight) {
        return (theoryMarks * theoryWeight) + (practicalMarks * practicalWeight);
    }
}

public class ExaminationSystem {
    public static void main(String[] args) {
        Examination exam = new ConcreteExamination();
        System.out.println("Total Marks: " + exam.evaluateMarks(450));
        System.out.println("Percentage: " + exam.evaluateMarks(450, 6));
        System.out.println("Result: " + exam.evaluateMarks(450, 6, 33));
        System.out.println("Weighted Score: " + exam.evaluateMarks(80, 90, 0.6, 0.4));
    }
}