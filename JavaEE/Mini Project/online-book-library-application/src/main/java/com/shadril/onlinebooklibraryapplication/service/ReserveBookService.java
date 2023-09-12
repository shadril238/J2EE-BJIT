package com.shadril.onlinebooklibraryapplication.service;

public interface ReserveBookService {
    void reserveBook(Long bookId);
    void cancelReserveBook(Long bookId);
}
