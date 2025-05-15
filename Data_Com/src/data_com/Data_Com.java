package data_com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Data_Com extends JFrame {
    private JTextField input1Field, input2Field, flagField, escapeField, resultField, resultField2;
    private JComboBox<String> comboBox, parityComboBox;

    public Data_Com() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Data Communication");
        setSize(1000, 700);
        setLocation(850, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Data Communication Techniques");
        titleLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        titleLabel.setBounds(350, 20, 300, 30);
        add(titleLabel);

        comboBox = new JComboBox<>(new String[]{
                "Select an Option",
                "Hamming Code",
                "Parity Check",
                "Bit Stuffing",
                "Bit Destuffing",
                "Character Stuffing",
                "Character Destuffing",
                "Cyclic Redundancy Check (CRC)"
        });
        comboBox.setBounds(350, 60, 300, 40);
        add(comboBox);

        JLabel input1Label = new JLabel("Input 1:");
        input1Label.setFont(new Font("Raleway", Font.BOLD, 18));
        input1Label.setBounds(50, 130, 100, 30);
        add(input1Label);

        input1Field = new JTextField();
        input1Field.setBounds(150, 130, 300, 35);
        input1Field.setFont(new Font("Raleway", Font.BOLD, 18));
        add(input1Field);

        JLabel input2Label = new JLabel("Input 2:");
        input2Label.setFont(new Font("Raleway", Font.BOLD, 18));
        input2Label.setBounds(500, 130, 100, 30);
        add(input2Label);

        input2Field = new JTextField();
        input2Field.setBounds(600, 130, 300, 35);
        input2Field.setFont(new Font("Raleway", Font.BOLD, 18));
        add(input2Field);

        JLabel flagLabel = new JLabel("Flag/Divisor:");
        flagLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        flagLabel.setBounds(50, 190, 100, 30);
        add(flagLabel);

        flagField = new JTextField();
        flagField.setBounds(150, 190, 300, 35);
        flagField.setFont(new Font("Raleway", Font.BOLD, 18));
        add(flagField);

        JLabel escapeLabel = new JLabel("Escape:");
        escapeLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        escapeLabel.setBounds(500, 190, 100, 30);
        add(escapeLabel);

        escapeField = new JTextField();
        escapeField.setBounds(600, 190, 300, 35);
        escapeField.setFont(new Font("Raleway", Font.BOLD, 18));
        add(escapeField);

        JLabel parityLabel = new JLabel("Parity Type:");
        parityLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        parityLabel.setBounds(50, 250, 100, 30);
        add(parityLabel);

        parityComboBox = new JComboBox<>(new String[]{"Even Parity", "Odd Parity"});
        parityComboBox.setBounds(150, 250, 300, 35);
        add(parityComboBox);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        resultLabel.setBounds(50, 320, 100, 30);
        add(resultLabel);

        resultField = new JTextField();
        resultField.setBounds(150, 320, 300, 40);
        resultField.setEditable(false);
        resultField.setFont(new Font("Raleway", Font.BOLD, 18));
        add(resultField);

        JLabel resultLabel2 = new JLabel("Error Position:");
        resultLabel2.setFont(new Font("Raleway", Font.BOLD, 18));
        resultLabel2.setBounds(500, 320, 100, 30);
        add(resultLabel2);

        resultField2 = new JTextField();
        resultField2.setBounds(600, 320, 300, 40);
        resultField2.setEditable(false);
        resultField2.setFont(new Font("Raleway", Font.BOLD, 16));
        add(resultField2);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Raleway", Font.BOLD, 18));
        calculateButton.setBounds(150, 410, 140, 40);
        add(calculateButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Raleway", Font.BOLD, 18));
        resetButton.setBounds(150, 460, 140, 40);
        add(resetButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCalculate();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onReset();
            }
        });
    }

    private void onCalculate() {
        String selectedOption = comboBox.getSelectedItem().toString();
        String input1 = input1Field.getText().trim();
        String input2 = input2Field.getText().trim();
        String flag = flagField.getText().trim();
        String escape = escapeField.getText().trim();
        String parityType = parityComboBox.getSelectedItem().toString();

        resultField.setText("");
        resultField2.setText("");

        switch (selectedOption) {
            case "Hamming Code":
                String[] hammingResults = hammingCode(input1, input2);
                resultField.setText(hammingResults[0]);
                resultField2.setText(hammingResults[1]);
                break;
            case "Parity Check":
                resultField.setText(parityCheck(input1, parityType));
                break;
            case "Bit Stuffing":
                resultField.setText(bitStuffing(input1, flag));
                break;
            case "Bit Destuffing":
                resultField.setText(bitDestuffing(input1, flag));
                break;
            case "Character Stuffing":
                resultField.setText(characterStuffing(input1, flag, escape));
                break;
            case "Character Destuffing":
                resultField.setText(characterDestuffing(input1, flag, escape));
                break;
            case "Cyclic Redundancy Check (CRC)":
                if (input1.isEmpty() || flag.isEmpty() || !isBinary(input1) || !isBinary(flag)) {
                    JOptionPane.showMessageDialog(this, "Data and divisor must be non-empty binary strings (0s and 1s).");
                    resultField.setText("Invalid input");
                    return;
                }
                String codeword = crcEncode(input1, flag);
                resultField.setText(codeword);
                if (!input2.isEmpty()) {
                    if (!isBinary(input2)) {
                        JOptionPane.showMessageDialog(this, "Received codeword must be a binary string.");
                        resultField2.setText("Invalid received codeword");
                    } else {
                        boolean noError = crcCheck(input2, flag);
                        resultField2.setText(noError ? "Successful" : "Error");
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Please select a valid option.");
        }
    }

    private void onReset() {
        input1Field.setText("");
        input2Field.setText("");
        flagField.setText("");
        escapeField.setText("");
        resultField.setText("");
        resultField2.setText("");
        comboBox.setSelectedIndex(0);
        parityComboBox.setSelectedIndex(0);
    }

    private boolean isBinary(String str) {
        return str.matches("[01]+");
    }

    private String crcEncode(String data, String divisor) {
        int divlen = divisor.length();
        String total = data + "0".repeat(divlen - 1);
        String remainder = crcDivision(total, divisor);
        return data + remainder;
    }

    private boolean crcCheck(String codeword, String divisor) {
        String remainder = crcDivision(codeword, divisor);
        return remainder.equals("0".repeat(divisor.length() - 1));
    }

    private String crcDivision(String dividend, String divisor) {
        int divlen = divisor.length();
        StringBuilder remainder = new StringBuilder(dividend);
        for (int i = 0; i <= dividend.length() - divlen; i++) {
            if (remainder.charAt(i) == '1') {
                for (int j = 0; j < divlen; j++) {
                    char bit = (remainder.charAt(i + j) == divisor.charAt(j)) ? '0' : '1';
                    remainder.setCharAt(i + j, bit);
                }
            }
        }
        return remainder.substring(dividend.length() - divlen + 1);
    }

    private String[] hammingCode(String data, String received) {
        if (data.isEmpty() || !isBinary(data)) {
            JOptionPane.showMessageDialog(this, "Input 1 must be binary (0s and 1s).");
            return new String[]{"", "Input error"};
        }

        int dataLength = data.length();
        int parityBits = 0;
        while (Math.pow(2, parityBits) < dataLength + parityBits + 1) {
            parityBits++;
        }
        int totalLength = dataLength + parityBits;

        char[] code = new char[totalLength];
        int dataIndex = 0;

        for (int i = 0; i < totalLength; i++) {
            if (i == 0 || i == 1 || i == 3 || (i > 3 && Math.pow(2, (int)(Math.log(i + 1)/Math.log(2))) == i + 1)) {
                code[i] = 'P';
            } else {
                code[i] = data.charAt(dataIndex++);
            }
        }

        for (int p = 0; p < parityBits; p++) {
            int parityPos = (int) Math.pow(2, p) - 1;
            int ones = 0;
            for (int i = 0; i < totalLength; i++) {
                if (i != parityPos && ((i + 1) & (1 << p)) != 0 && code[i] != 'P') {
                    if (code[i] == '1') ones++;
                }
            }
            code[parityPos] = (ones % 2 == 0) ? '0' : '1';
        }

        StringBuilder hammingCode = new StringBuilder();
        for (char c : code) {
            hammingCode.append(c);
        }

        if (received.isEmpty()) {
            return new String[]{hammingCode.toString(), ""};
        }

        if (!isBinary(received) || received.length() != totalLength) {
            JOptionPane.showMessageDialog(this, "Input 2 must be binary and " + totalLength + " bits long.");
            return new String[]{"", "Input error"};
        }

        int errorPos = 0;
        for (int p = 0; p < parityBits; p++) {
            int parityPos = (int) Math.pow(2, p) - 1;
            int ones = 0;
            for (int i = 0; i < totalLength; i++) {
                if (((i + 1) & (1 << p)) != 0) {
                    if (received.charAt(i) == '1') ones++;
                }
            }
            if (ones % 2 != 0) {
                errorPos += (parityPos + 1);
            }
        }

        String result2;
        if (errorPos == 0) {
            result2 = "No error detected";
        } else {
            StringBuilder corrected = new StringBuilder(received);
            corrected.setCharAt(errorPos - 1, received.charAt(errorPos - 1) == '1' ? '0' : '1');
            result2 = "Error at pos: " + errorPos + " (Cor: " + corrected + ")";
        }

        return new String[]{hammingCode.toString(), result2};
    }

    private String parityCheck(String input, String parityType) {
        try {
            int number = Integer.parseInt(input);
            if (number < 0) {
                JOptionPane.showMessageDialog(this, "Please enter a non-negative number.");
                return "";
            }

            String binary = Integer.toBinaryString(number);
            int oneCount = 0;
            for (char c : binary.toCharArray()) {
                if (c == '1') oneCount++;
            }

            String parityBit;
            String resultParity;
            if (parityType.equals("Even Parity")) {
                parityBit = (oneCount % 2 == 0) ? "0" : "1";
                resultParity = "Even parity";
            } else {
                parityBit = (oneCount % 2 == 0) ? "1" : "0";
                resultParity = "Odd parity";
            }

            return parityBit + binary + " (" + resultParity + ")";
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
            return "";
        }
    }

    private String bitStuffing(String data, String flag) {
        if (!isBinary(data) || !isBinary(flag)) {
            JOptionPane.showMessageDialog(this, "Data and flag must be binary strings.");
            return "";
        }
        return flag + data.replaceAll("11111", "111110") + flag;
    }

    private String bitDestuffing(String data, String flag) {
        if (!isBinary(data) || !isBinary(flag)) {
            JOptionPane.showMessageDialog(this, "Data and flag must be binary strings.");
            return "";
        }
        if (data.startsWith(flag) && data.endsWith(flag)) {
            return data.substring(flag.length(), data.length() - flag.length()).replaceAll("111110", "11111");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid data format: Missing flags.");
            return "";
        }
    }

    private String characterStuffing(String data, String flag, String esc) {
        if (flag.isEmpty() || esc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Flag and escape must not be empty.");
            return "";
        }
        StringBuilder stuffedData = new StringBuilder();
        stuffedData.append(flag).append(" ");
        for (int i = 0; i < data.length(); i++) {
            char currentChar = data.charAt(i);
            if (currentChar == flag.charAt(0) || currentChar == esc.charAt(0)) {
                stuffedData.append(esc);
            }
            stuffedData.append(currentChar);
        }
        stuffedData.append(" ").append(flag);
        return stuffedData.toString();
    }

    private String characterDestuffing(String data, String flag, String esc) {
        if (flag.isEmpty() || esc.isEmpty() || !data.startsWith(flag + " ") || !data.endsWith(" " + flag)) {
            JOptionPane.showMessageDialog(this, "Invalid data format or missing flag/escape.");
            return "";
        }
        StringBuilder destuffedData = new StringBuilder();
        String payload = data.substring(flag.length() + 1, data.length() - (flag.length() + 1));
        for (int i = 0; i < payload.length(); i++) {
            if (payload.charAt(i) == esc.charAt(0)) {
                i++;
            }
            destuffedData.append(payload.charAt(i));
        }
        return  destuffedData.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Data_Com().setVisible(true));
    }
}