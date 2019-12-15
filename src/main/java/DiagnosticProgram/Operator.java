package DiagnosticProgram;

public enum Operator {
  ADD {
    @Override
    public int apply(int a, int b) {
      return a + b;
    }
  },
  MULTIPLY {
    @Override
    public int apply(int a, int b) {
      return a * b;
    }
  };

  public static final int HALT = 99;

  public abstract int apply(int a, int b);

  public static Operator[] operators() {
    return Operator.values();
  }
  }

