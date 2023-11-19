\documentclass{article}
\usepackage{graphicx}
\usepackage{amsmath}

\begin{document}

\title{Análisis de la Complejidad de los Algoritmos de Checksum}
\author{Tu nombre}
\date{\today}

\maketitle

\section{Introducción}

En este informe, presentamos un análisis de la complejidad de dos algoritmos de checksum: single y dual checksum. Ambos algoritmos fueron implementados en Scala y se midió su tiempo de ejecución para diferentes tamaños de datos.

\section{Complejidad de los Algoritmos}

La complejidad de los algoritmos de checksum se puede calcular en función del número de bloques del dato.

\subsection{Single Checksum}

Para el algoritmo single checksum, la complejidad es $O(n)$, donde $n$ es el número de bloques del dato. Esto se debe a que el algoritmo realiza una sola suma modular por cada bloque, que es una operación constante. Por lo tanto, el tiempo de ejecución es proporcional al tamaño del dato.

\subsection{Dual Checksum}

Para el algoritmo dual checksum, la complejidad también es $O(n)$, donde $n$ es el número de bloques del dato. Esto se debe a que el algoritmo realiza dos sumas modulares por cada bloque, que son operaciones constantes. Por lo tanto, el tiempo de ejecución es proporcional al tamaño del dato, pero con un factor de 2 mayor que el single checksum.

\section{Resultados Experimentales}

Se generaron datos aleatorios de diferentes tamaños, desde 1 KB hasta 10 MB, y se midió el tiempo de ejecución de cada algoritmo. Los resultados se muestran en la Figura 1.

\begin{figure}[h]
\centering
\includegraphics[width=0.8\textwidth]{grafica.png}
\caption{Tiempo de ejecución de los algoritmos de checksum en función del tamaño del dato.}
\end{figure}

Como se puede observar en la gráfica, el tiempo de ejecución de ambos algoritmos es proporcional al tamaño del dato, lo cual es consistente con su complejidad teórica $O(n)$. Sin embargo, el algoritmo dual checksum tiene un tiempo de ejecución mayor que el single checksum, debido al factor de 2 en su complejidad.

\section{Conclusión}

Los resultados experimentales confirman la complejidad teórica de los algoritmos de checksum. Esto demuestra que la complejidad teórica es una buena indicación del comportamiento del tiempo de ejecución de un algoritmo en la práctica.

\end{document}
