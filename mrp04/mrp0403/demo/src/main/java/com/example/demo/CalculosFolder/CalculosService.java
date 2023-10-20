package com.example.demo.CalculosFolder;

import java.util.ArrayList;
import java.util.TreeSet;

import org.springframework.ui.Model;

public interface CalculosService{
    public boolean obtPrimo(String num,Model model);
    public Double obtHipotenusa(Integer X, Integer Y);
    public TreeSet<Integer> obtCalculosSinRepetidos(Integer X);
    public ArrayList<Integer> obtDivisores(int X);
}