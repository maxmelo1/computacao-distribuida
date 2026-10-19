const express   = require('express')
const cors      = require('cors')

const app   = express()
const port  = 3000;

app.use(cors())
app.use(express.json());

let produto = {
    nome: "notebook",
    preco: 5500.00
}

app.get('/api/produtos', (req, res) =>{
    res.status(200).json(produto)
})

app.post('/api/produtos', (req, res) => {
    const prod = req.body

    prod.nome = prod.nome + " cadastrado com sucesso"

    res.status(201).json(prod);
})

app.listen( port, () =>{
    console.log(`API rodando na porta: ${port}`)
})