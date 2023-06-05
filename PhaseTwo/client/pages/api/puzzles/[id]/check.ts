import type { NextApiRequest, NextApiResponse } from 'next'

interface Data {
    success: boolean
}

export default async function handler(
    req: NextApiRequest,
    res: NextApiResponse<Data>
) {
    const { id } = req.query;
    const { content } = req.body;
    const response = await fetch(`http://localhost:8080/api/puzzles/${id}/check`, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "content": content,
        })
    });
    const data: Data = await response.json();
    res.status(200).json(data);

}
