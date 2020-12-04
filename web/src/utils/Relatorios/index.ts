import { jsPDF } from "jspdf";
import api from '../../services/api';
import logo from '../../assets/images/logo-extended.jpeg'

async function createRelatorios(){
  
    const doc = new jsPDF();
    const d = new Date();
    var obj = {}
    var y = 60
    var i = 0
   
    await api.get('/gerar-relatorios').then(async relatorios => {
       obj = relatorios.data
    })

    doc.addImage(logo, "JPEG", 80, 5, 70, 40);
    doc.setFontSize(35)
    doc.text("Relatórios Morais Library", 40, 45)
    doc.setFontSize(20)
    doc.text("Dados cadastrais: ", 10, y)
    doc.setFontSize(15)
    for(let k of Object.values(obj)) {
        if(i === 5) {
            doc.setFontSize(20)
            doc.text("Dados emprestimos: ", 10, y+=10)
            doc.setFontSize(15)
        } else if(i === 7) {
            doc.setFontSize(20)
            doc.text("Dados Reservas:", 10, y+=10)
            doc.setFontSize(15)
        } else if (i === 11) {
            doc.setFontSize(20)
            doc.text("Dados Eventos: ", 10, y+=10)
            doc.setFontSize(15)
        }
        doc.text(k + "", 15, y+=10)
        i++
    }
    var dataHoje = d.getDay() + "-" + d.getMonth() + "-" + d.getFullYear();
    var pdfName = "Relatorios-" + dataHoje + ".pdf"
    doc.save(pdfName);

}

export default createRelatorios;
