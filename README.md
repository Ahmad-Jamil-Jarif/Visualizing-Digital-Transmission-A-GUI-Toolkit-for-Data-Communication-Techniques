# Visualizing Digital Transmission: A GUI Toolkit for Data Communication Techniques

This project is an interactive GUI application built with Java Swing that simulates and visualizes key data communication techniques, including Hamming Code, Parity Check, Bit Stuffing, Character Stuffing, and Cyclic Redundancy Check (CRC). It serves as an educational tool for students, teachers, and network engineers to understand these concepts through practical demonstrations.

## Features

- **Hamming Code Generator and Error Detector**: Simulate error detection and correction.
- **Even and Odd Parity Generator and Checker**: Demonstrate parity bit calculations.
- **Bit Stuffing and De-Stuffing Module**: Show how bit stuffing prevents data corruption.
- **Character Stuffing and De-Stuffing Module**: Illustrate character stuffing for data framing.
- **CRC Generator and Verifier**: Compute and verify CRC checksums.

## System Requirements

- Java Development Kit (JDK) 17 or higher
- Operating System: Windows, macOS, or Linux

## Installation

To run this application, you need to have Java installed on your system. Follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/your-repo-name.git
   ```
2. **Navigate to the project directory**:
   ```bash
   cd your-repo-name
   ```
3. **Compile the Java files**:
   ```bash
   javac -d bin src/*.java
   ```
4. **Run the application**:
   ```bash
   java -cp bin MainClassName
   ```

*Note: Replace `MainClassName` with the actual name of the main class in the project.*

## Usage

Once the application is running, interact with it through the graphical user interface:

1. **Select a module** from the navigation menu (e.g., Hamming Code, Parity Check).
2. **Input the required data** in the provided fields (e.g., binary string).
3. **Click "Calculate" or "Simulate"** to process the input and view the results.
4. **Observe the visual feedback** and any error messages displayed.

The application provides real-time visualization and feedback for each data communication technique, making it easier to understand how they work.

## Limitations and Future Work

### Limitations

- Supports only binary data; limited support for ASCII or Unicode strings.
- Error scenarios must be manually introduced; no automated random error generation.
- Only standard CRC polynomials are supported.
- GUI design is functional but could be enhanced for better user experience.

### Future Work

- Add support for additional techniques like Manchester Encoding and Checksum.
- Improve GUI aesthetics using JavaFX or web-based alternatives.
- Include tutorials or step-by-step animated explanations.
- Deploy as a web-based tool for broader accessibility.

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Submit a pull request with a clear description of your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For questions or feedback, please contact [your email] or open an issue on GitHub.

## About

This project was developed as part of the Data Communication Lab (CSE-312) course at Green University of Bangladesh by Ahmad Jamil Jarif (231902007) and MD Asraful Hasan (231902011).
