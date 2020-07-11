package com.cabinvoicegenerator.exception;

public class InvoiceGeneratorException extends Exception {
    public enum ExceptionType {
        DUPLICATE_USER
    }

    public ExceptionType type;

    public InvoiceGeneratorException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
