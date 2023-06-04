import type { NextApiRequest, NextApiResponse } from 'next'

type Data = {
  name: string
}

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse<Data>
) {
  const { id } = req.query;
  const response = await fetch(`http://localhost:8080/api/puzzle-types/${id}/puzzles/random`)
  const data = await response.json();
  res.status(200).json(data);
}
